// app.js
App({
    onLaunch: function () {
        wx.login({
          success: function (res) {
            if (res.code) {
              //发起网络请求
              wx.request({
                url: 'http://localhost:9000/wx/login',
                data: {
                  code: res.code
                }
              })
            } else {
              console.log('登录失败！' + res.errMsg)
            }
          }
        });
    
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
      // server: 'http://localhost:9000/wx',
        server: 'https://love.5918j.com/wx/',
        music_url: ''
    }
});

