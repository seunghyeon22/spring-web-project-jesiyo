

async function cancel(userId,goodsId, tryPrice){
    let userDetails =  await getAuthentication()
    let url =`/api/v1/cancelBid`
    let response = await fetch(url, {
        method : "POST",
        body : JSON.stringify({
                  "userId" : userDetails.id,
                  "goodsId":goodsId,
                  "tryPrice" :  tryPrice}),
        headers : {
            "Content-Type" : "Application/JSON; cherset = utf-8"
        }
    })
    let responseData = await response.json();
    if(!responseData.success){
        throw  new Error("네트워크 응답에 문제가 있습니다.")
    }else {
        alert("입찰을 취소하였습니다.")
    }
    window.location.href = "/s/mypage-participating-auction";

}

let bid_Id ="";
let goods_Id ="";
let try_Price ="";

function addReBid(id,goodsId,buyerTryPrice){
    bid_Id = id;
    goods_Id = goodsId;
    try_Price = buyerTryPrice;
}

async function reRid(){
    let re_Bid = document.getElementById("t1-re-bid-price").value;
    let url = "/api/v1/re-bid"
    let response =await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify({
            bidId: bid_Id,
            goodsId: goods_Id,
            tryPrice : try_Price,
            reTryPrice: re_Bid
        })
    });
    let responseData = await response.json();
    console.log(responseData)
    if(!responseData.success){
        alert(responseData.message);
    }else{
        alert("입찰가를 수정하였습니다.")
    }
    window.location.href = `/s/mypage-participating-auction`;
}

