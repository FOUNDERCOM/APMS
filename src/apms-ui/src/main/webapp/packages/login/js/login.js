var Login = function() {

    var handleLogin = function() {
        var form = $('.login-form');

        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
                username: {
                    required: "请填写用户名."
                },
                password: {
                    required: "请填写密码."
                }
            },

            invalidHandler: function() { //display error alert on form submit
                $('.needAccountAndPwd', form).show();
            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
                form.submit(); // form validation success, call ajax form submit
            }
        });
        var login = function() {
            var url = "mvc/dispatch";
            url += "?controller=LoginController&method=login";
            url += "&account=" + $("input[name='username']").val();
            url += "&pwd=" + $("input[name='password']").val();
            $.ajax({
                url: url
            }).done(function(success) {
                if (!success['result']) {
                    $('.wrongAccountAndPwd').show();
                } else {
                    location.href="index.html#/home.html";
                }
            });
        };

        $('.login-form input').keypress(function(e) {
            if (e.which === 13) {
                if (form.validate().form()) {
                    login();
                }
                return false;
            }
        });

        $('.login-btn').click(function() {
            if (form.validate().form()) {
                login();
            }
            return false;
        });
    };




    return {
        //main function to initiate the module
        init: function() {

            handleLogin();

            // init background slide images
            $('.login-bg').backstretch([
                "packages/login/img/bg1.jpg",
                "packages/login/img/bg2.jpg",
                "packages/login/img/bg3.jpg",
                "packages/login/img/bg4.jpg",
                "packages/login/img/bg5.jpg"
                ], {
                  fade: 1000,
                  duration: 4000
                }
            );

            $('.forget-form').hide();

        }

    };

}();

$(document).ready(function() {
    Login.init();
});
