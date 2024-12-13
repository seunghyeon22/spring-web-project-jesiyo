
async function cancel(userId,goodsId, tryPrice){
    let url =`/api/v1/cancelBid`
    let response = await fetch(url, {
        method : "POST",
        body : JSON.stringify({
                  "userId" : userId,
                  "goodsId":goodsId,
                  "tryPrice" :  tryPrice}),
        headers : {
            "Content-Type" : "Application/JSON; cherset = utf-8"
        }
    })
    let responseData = await response.json();
    if(!responseData.success){{
        throw  new Error("네트워크 응답에 문제가 있습니다.")
    }}
    window.location.href = "/myPage-participating-auction";

}