function toSignin() {
    window.location.href = "/home/signin"
}

function toSignup() {
    window.location.href = "/home/signup"
}

function confirmSignup() {
    let username = $('#inputUsername').val();
    let nickname = $('#inputNickname').val();
    let password = $('#inputPassword').val();
    let introduction = $('#inputIntroduction').val();

    $.ajax({
        type: "POST",
        url: "/api/auth/signup",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            username: username,
            nickname: nickname,
            password: password,
            introduction: introduction
        })
    })
        .done(function () {
                alert('회원가입 완료!');
                window.location.href = "/home";
            }
        )
        .fail(function (xhr) {
            alert('회원가입 오류!');
            alert("상태 코드 " + xhr.status + ": " + xhr.responseJSON.message);
        });
}

function confirmSignin() {
    let username = $('#inputUsername').val();
    let password = $('#inputPassword').val();

    $.ajax({
        type: "POST",
        url: "/api/auth/signin",
        contentType: "application/json",
        data: JSON.stringify({username: username, password: password})
    })
        .done(function (res, status, xhr) {
            const token = xhr.getResponseHeader('Authorization');

            // 쿠키 저장
            Cookies.set('Authorization', token, {path: '/', expires: expirationDate});

            $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
                jqXHR.setRequestHeader('Authorization', token);
            });
            alert("로그인 성공!");

            window.location.href = "/home/mainpage";
        })
        .fail(function (xhr) {
            alert('로그인 오류!');
            alert("상태 코드 " + xhr.status + ": " + xhr.responseJSON.message);
        });
}

function changePassword() {
    let currentPassword = $('#currentPassword').val();
    let changedPassword = $('#changedPassword').val();
    let confirmedPassword = $('#confirmedPassword').val();

    $.ajax({
        type: "PUT",
        url: "/api/users/password",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            currentPassword: currentPassword,
            changedPassword: changedPassword,
            confirmedPassword: confirmedPassword
        }),
        headers: {'Authorization': authCookie},
    })
        .done(function (xhr) {
            console.log(xhr);
            alert("비밀번호 변경 성공!");

            logout();
        })
        .fail(function (xhr) {
            alert('비밀번호 변경 오류!');
            alert("상태 코드 " + xhr.status + ": " + xhr.responseJSON.message);
        });
}

function logout() {
    // 토큰 삭제
    Cookies.remove('Authorization', {path: '/'});
    window.location.href = "/home";
}

function toChangePassword() {
    window.location.href = "/home/changepassword";
}

function toCheckPassword() {
    window.location.href = "/home/checkpassword";
}