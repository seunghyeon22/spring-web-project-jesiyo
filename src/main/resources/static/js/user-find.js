// 전화번호에 숫자만 입력가능 알림창
// 전화번호에 숫자만 입력가능 알림창
function telCheck() {
    let tel = document.querySelector(".tel").value; // 입력 값 가져오기

    if (tel && isNaN(tel)) { // tel이 비어있지 않고, 숫자가 아닐 경우
        alert("숫자만 입력해주세요.");
    }
}

document.querySelector("#tel").addEventListener('keyup', telCheck);
document.querySelector("#telforpw").addEventListener('keyup', telCheck);
