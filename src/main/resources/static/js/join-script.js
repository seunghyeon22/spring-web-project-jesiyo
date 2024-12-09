
// 우편번호 검색
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;


            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if (data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if (data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}



// 아이디 유효성 검증
function idCheck() {
    let id = document.getElementById('id').value;
    let idck = document.getElementById('idck');
    let regex = /^[a-zA-Z][a-zA-Z0-9]*$/; // 영문자로 시작하고 영문자와 숫자만 포함

    idck.textContent = '';
    idck.style.color = '';

    if (id.length < 3 || id.length > 12) {
        idck.textContent = '아이디는 3자 이상 12자 이하이어야 합니다.';
        idck.style.color = 'red';
        return;
    }

    if (id.includes(" ")) {
        idck.textContent = '아이디는 공백을 포함할 수 없습니다.';
        idck.style.color = 'red';
        return;
    }

    if (!regex.test(id)) {
        idck.textContent = '아이디는 영문자로 시작하고, 영문자와 숫자만 포함할 수 있습니다.';
        idck.style.color = 'red';
        return;
    }

    idck.textContent = '유효한 아이디입니다.';
    idck.style.color = 'green';
}

document.getElementById('id').addEventListener('keyup', idCheck);



// 비밀번호 일치 
function pwCheck() {
    const pw1 = document.getElementById('pw1').value;
    const pw2 = document.getElementById('pw2').value;
    const pwck = document.getElementById('pwck');
    if( pw1&&pw2 != null){
        if (pw1 == pw2) {
            pwck.textContent = '비밀번호 일치';
            pwck.style.color = 'green';
        } else {
            pwck.textContent = '비밀번호 불일치';
            pwck.style.color = 'red';
        }
    }else{
        pwck.textContent = '비밀번호를 입력해주세요';
    }
}

document.getElementById('pw1').addEventListener('keyup', pwCheck);
document.getElementById('pw2').addEventListener('keyup', pwCheck);



// 전화번호에 숫자만 입력가능 알림창
function telCheck() {
    let tel = document.getElementById('tel').value;

    if (isNaN(tel)) {
        alert("숫자만 입력해주세요.");
    }
}

document.getElementById('tel').addEventListener('keyup',telCheck);







