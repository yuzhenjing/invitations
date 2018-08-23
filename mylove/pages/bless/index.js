// pages/bless/index.js

const app = getApp()
var server = app.globalData.server;
var appid = app.globalData.appid;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title:'',
    subtitle:'',
    userInfo: {},
    zanLog:[],
    page: 0,
    hasMoreData: true,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    let userInfo = wx.getStorageSync('userInfo')
    if (userInfo) {
      this.setData({
        userInfo: userInfo
      })
    }
    /**调用查询方法进行查询 */
    that.queryWish();

  },

  queryWish: function() {
    var that = this;
    wx.showLoading({ title: '拼命加载中...' })
    that.setData({ subtitle: '加载中...' })   
    wx.request({
      url: server +'/queryFriendWish',
      method: 'POST',
      data: {
        'c': 'info',
        'appId': appid,
        'startNum': that.data.page
      },
      header: {
        'Accept': 'application/json'
      },
      success: function(res) {
        that.setData({
          hasMoreData: true,
          mainInfo: res.data.mainInfo,
          zanLog: res.data.friendWishList,
          zanNum: res.data.wishCount,
          page: that.data.page + 1
        });
        wx.hideLoading()
      }
    })
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    // var that = this;
    // this.setData({ zanLog: [], page: that.data.page, hasMore: true })
    // this.queryWish();
  },
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    // var that = this;
    // this.setData({ zanLog: [], page: that.data.page, hasMore: true })
    // this.queryWish();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {
    var that = this;
    //console.log(that.data);
    return {
      title: that.data.mainInfo.share,
      imageUrl: that.data.mainInfo.thumb,
      path: 'pages/index/index',
      success: function(res) {
        wx.showToast({
          title: '分享成功',
        })
      },
      fail: function(res) {
        // 转发失败
        wx.showToast({
          title: '分享取消',
        })
      }
    }
  },
  bindgetuserinfo: function(e) {
    console.log(e.detail.userInfo)
    var that = this;
    if (e.detail.userInfo) {
      wx.setStorageSync('userInfo', e.detail.userInfo)
      that.setData({
        userInfo: e.detail.userInfo,
        authBtn: false
      })
      that.sendWish();
    } else {
      wx.showToast({
        title: "为了您更好的体验,请先同意授权",
        icon: 'none',
        duration: 2000
      });
    }
  },

  sendWish: function() {
    var that = this;
    var userInfo = that.data.userInfo;

    wx.request({
      url: server + '/sendBlessing',
      data: {
        'c': 'zan',
        'appId': appid,
        'nickName': userInfo.nickName,
        'avatarUrl': userInfo.avatarUrl,
        'gender': userInfo.gender,
        'city': userInfo.city,
        'country': userInfo.country,
        'province': userInfo.province,
        'startNum': 0
      },
      header: {},
      method: "POST",
      dataType: "json",
      success: res => {
        // console.log(res.data);
        if (res.data.success) {

          that.setData({
            zanLog: res.data.friendWishList,
            zanNum: res.data.wishCount
          });
          wx.showModal({
            title: '提示',
            content: res.data.msg,
            showCancel: false
          })
        } else {
          wx.showModal({
            title: '提示',
            content: res.data.msg,
            showCancel: false
          })
        }
      }
    })
  },
})