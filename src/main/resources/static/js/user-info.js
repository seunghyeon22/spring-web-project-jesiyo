let isnotExceeded = false;

function onSubmitCheck(){
    return isnotExceeded;
}

// 계좌번호에 숫자만 입력가능 알림창
function accountCheck() {
    let out = document.querySelector('#outAccount').value;

    if (isNaN(out)) {
        alert("숫자만 입력해주세요.");
    }
}
document.querySelector('#outAccount').addEventListener('keyup',accountCheck);

// 가지고 있는 금액보다 많은 돈 출금 안돼!
function ban(){
    let now = parseInt(document.querySelector('#nowMoney').value);
    let out = parseInt(document.querySelector('#outMoney').value);

    if(now < out){
        alert("보유금액보다 많은 금액을 출금하실 수 없습니다.")
        isnotExceeded = false;
    }else if(now >= out){
        isnotExceeded = true;
    }
}

document.addEventListener('DOMContentLoaded', function () {
    updateThermometer();
});

function updateThermometer() {
    const thermometerFill = document.querySelector('.thermometer-fill');
    const percentage = document.querySelector('#percentage').value;

    thermometerFill.style.height = `${percentage}%`;
}



