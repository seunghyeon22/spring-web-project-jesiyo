let isPasswordValid = false;
let isPasswordSame = false;

function onSubmitCheck(){
    return isPasswordValid && isPasswordSame;
}

// 비밀번호 유효성 검사
function pwCheck(password) {
    const hasLetter = /[a-zA-Z]/.test(password); // 영문자 포함
    const hasNumber = /[0-9]/.test(password); // 숫자 포함
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password); // 특수문자 포함

    if (password.length < 8) {
        isPasswordValid = false;
        return '비밀번호는 최소 8자 이상이어야 합니다.';
    }
    else if (!hasLetter) {
        isPasswordValid = false;
        return '비밀번호에는 영문자가 포함되어야 합니다.';
    }
    else if (!hasNumber) {
        isPasswordValid = false;
        return '비밀번호에는 숫자가 포함되어야 합니다.';
    }
    else if (!hasSpecialChar) {
        isPasswordValid = false;
        return '비밀번호에는 특수문자가 포함되어야합니다.';
    }
    else {
        isPasswordValid = true;
        return "정상입니다";
    }

}

// 비밀번호 일치 확인
function pwAcc() {
    const pw1 = document.querySelector('#pw1').value;
    const pw2 = document.querySelector('#pw2').value;

    console.log(pw1);
    console.log(pw2);

    const pwck = document.querySelector('#pwck');
    const pwvalid = document.querySelector('#pwvalid');

    const msg = pwCheck(pw1);

    console.log(msg);

    pwvalid.style.color = 'orange';
    pwvalid.textContent = msg;


    if (pw1 && pw2 != null) {
        if (pw1 == pw2) {
            pwck.textContent = '비밀번호 일치';
            pwck.style.color = 'green';
            isPasswordSame = true;
        } else {
            pwck.textContent = '비밀번호 불일치';
            pwck.style.color = 'red';
            isPasswordSame = false;
        }
    }
}

document.getElementById('pw1').addEventListener('keyup', pwAcc);
document.getElementById('pw2').addEventListener('keyup', pwAcc);
