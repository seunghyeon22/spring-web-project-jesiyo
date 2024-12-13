

async function deleteGoods(goodsId){
    let url = `/api/v1/bid-delete/${goodsId}`
    let response = await fetch(url,{
        method : "DELETE"
    });
    let responseData = await response.json();
    if(!responseData.success){
        throw new Error("네트워크 응답에 문제가 있습니다.");
    }
    window.location.href="/myPage-being-auctioned";
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
    if(!responseData.success){
        throw new Error("네트워크 응답에 문제가 있습니다.");
    }
    window.location.href="/myPage-being-auctioned";
}