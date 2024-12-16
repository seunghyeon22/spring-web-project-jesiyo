

async function deleteGoods(goodsId){
    let url = `/api/v1/bid-delete/${goodsId}`
    let response = await fetch(url,{
        method : "DELETE"
    });
    let responseData = await response.json();
    if(responseData.success){
        alert("물품 경매를 취소하였습니다.")
    } else {
        alert("경매 취소에 실패했습니다.")
    }
    window.location.href="/s/mypage-being-auctioned";
}
async function  insertTransaction(goodsId){
    let url = `/api/v1/early-transaction`;
    let response = await fetch(url, {
        method : "POST",
        body : goodsId,
        headers:{
            "Content-Type": "application/json",
        }
    });
    let responseData = await response.json();
    if(responseData.success){
        alert("경매 조기 종료에 성공하였습니다.")
    } else{
        alert("입찰자가 없어 조기 종료 할 수 없습니다. 경매를 끝내려면 경매 취소 버튼을 눌러주세요")
    }
    window.location.href="/s/mypage-being-auctioned";
}