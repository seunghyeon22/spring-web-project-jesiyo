// 경매시작가 입력 시 숫자가 아니면 알림창
function startingPriceCheck() {
    let start_price = document.getElementById('start_price').value;

    if (isNaN(start_price)) {
        alert("숫자만 입력해주세요.");
    }
}
document.getElementById('start_price').addEventListener('keyup',startingPriceCheck);

