var main = {
    init : function () {
        var _this = this;
        $('#btn-strategy-save').on('click', function () {
            _this.save_strategy();
        });
        $('#btn-register-save').on('click', function () {
            _this.save_register();
        });
    },

    save_strategy : function () {
        var data = {
            coinName: $('#coinName').val(),
            riseRate: $('#riseRate').val(),
            declineRate: $('#declineRate').val(),
            content: $('#content').val()
        };

        var WshShell = new ActiveXObject("WScript.Shell");
        WshShell.Run('C:\\yonsei2021\\pythonProject\\dist\\main\\main.exe');

        $.ajax({
            type: 'POST',
            url: '/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    },

    save_register : function () {
        var data = {
            userName: $('#userName').val(),
            userId: $('#userId').val(),
            password: $('#password').val(),
            accessKey: $('#accessKey').val(),
            secretKey: $('#secretKey').val()
        };

        $.ajax({
            type: 'POST',
            url: '/register',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원가입이 완료되었습니다.');
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    }

};

main.init();