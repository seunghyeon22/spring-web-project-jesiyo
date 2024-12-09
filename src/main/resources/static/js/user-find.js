// 전화번호에 숫자만 입력가능 알림창
function telCheck() {
    let tel = document.querySelector(".tel")

    if (isNaN(tel)) {
        alert("숫자만 입력해주세요.");
    }
}

document.querySelector("#tel").addEventListener('keyup', telCheck);
document.querySelector("#telforpw").addEventListener('keyup', telCheck);
