// app.js
App({
    onLaunch: function () {
        //var that = this;
        if (this.globalData.userInfo) {
            typeof cb == "function" && cb(this.globalData.userInfo)
        } else {
            // 调用登录接口
            wx.login({
                success: function () {
                    wx.getUserInfo({
                        success: function (res) {
                            that.globalData.userInfo = res.userInfo;
                            typeof cb == "function" && cb(that.globalData.userInfo)
                        }
                    })
                }
            });
        }

    },
    onHide: function () {
        wx.pauseBackgroundAudio();
    },
    onShow: function () {
        wx.playBackgroundAudio()
    },
    globalData: {
        userInfo: null,
        appid: 'wx1c9835e404cc3d69',
      // server: 'http://192.168.56.1:9000/wx',
        server: 'https://love.5918j.com/wx/',
        music_url: ''
    }
});

