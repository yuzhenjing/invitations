// pages/chat/index.js
const app = getApp()
var server = app.globalData.server;
var appid = app.globalData.appid;

Page({

    /**
     * 页面的初始数据
     */
    data: {
        userInfo: {},
        inputValue: ''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this

        // wx.getUserInfo({
        //   success: function (res) {
        //     that.setData({
        //       userInfo: res.userInfo
        //     })
        //   }
        // })

        let userInfo = wx.getStorageSync('userInfo')
        if (userInfo) {
            this.setData({
                userInfo: userInfo
            })
        }

        wx.request({
            url: server +'/queryLeaveWord',
            method: 'POST',
            data: {
                'c': 'info',
                'appId': appid,
                'startNum':0
            },
            header: {
                'Accept': 'application/json'
            },
            success: function(res) {
              console.log(res.data)
                that.setData({
                    mainInfo: res.data.mainInfo,
                    chatList: res.data.leaveWords,
                    msgCount: res.data.msgCount
                });
            }
        })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {

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
    bindKeyInput: function(e) {
        this.setData({
            inputValue: e.detail.value
        })
        
    },
    bindgetuserinfo: function (e) {
        console.log(e.detail.userInfo)
        var that = this;
        if (e.detail.userInfo) {
            wx.setStorageSync('userInfo', e.detail.userInfo)
            that.setData({
                userInfo: e.detail.userInfo,
                authBtn: false
            })
            console.log(1,e.detail.userInfo)
            //that.foo()

        } else {
            wx.showToast({
                title: "为了您更好的体验,请先同意授权",
                icon: 'none',
                duration: 2000
            });
        }
    },
    foo: function() {
        var that = this
        console.log(2,that.data.inputValue)
        if (that.data.inputValue) {
            //留言内容不是空值

            var userInfo = that.data.userInfo;
            var name = userInfo.nickName;
            var face = userInfo.avatarUrl;
            var words = that.data.inputValue;
            wx.request({
                url: server+'/sendMessage',
                data: {
                    'c': 'send',
                    'appId': appid,
                    'nickName': name,
                    'avatarUrl': face,
                    'message': words
                },
                header: {},
                method: "POST",
                dataType: "json",
                success: res => {
                    if (res.data.success) {
                        that.setData({
                            chatList: res.data.leaveWords,
                            msgCount: res.data.msgCount
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
        } else {
            //Catch Error
            wx.showModal({
                title: '提示',
                content: '您还没有填写内容',
                showCancel: false
            })
        }
        that.setData({
            inputValue: '' //将data的inputValue清空
        });
        return;
    }
})