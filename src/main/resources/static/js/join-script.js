/*
let isUsernameVaild = false;
let isUsernameOk = false;
let isPasswordValid = false;
let isPasswordSame = false;

function onSubmitCheck(){
    return isUsernameVaild && isUsernameOk && isPasswordValid && isPasswordSame;
}
*/


// 아이디 유효성 검증
function idCheck() {
    let id = document.querySelector('#id').value;
    let idck = document.querySelector('#idck');
    let regex = /^[a-zA-Z][a-zA-Z0-9]*$/; // 영문자로 시작하고 영문자와 숫자만 포함

    idck.textContent = '';
    idck.style.color = '';

    if (id.length < 3 || id.length > 12) {
        idck.textContent = '아이디는 3자 이상 12자 이하이어야 합니다.';
        idck.style.color = 'red';
        isUsernameVaild = false;
        return;
    }else if (id.includes(" ")) {
        idck.textContent = '아이디는 공백을 포함할 수 없습니다.';
        idck.style.color = 'red';
        isUsernameVaild = false;
        return;
    }else if (!regex.test(id)) {
        idck.textContent = '아이디는 영문자로 시작하고, 영문자와 숫자만 포함할 수 있습니다.';
        idck.style.color = 'red';
        isUsernameVaild = false;
        return;
    }else {
        idck.textContent = '유효한 아이디입니다.';
        idck.style.color = 'green';
        isUsernameVaild = true;
    }
}

document.getElementById('id').addEventListener('keyup', idCheck);



// 아이디 중복 체크
async function idDupCheck(){
    let idInput = document.querySelector('#id');

    let username = idInput.value;

    try {
        const response = await fetch('/check-id', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username:username }),
        });

        if (!response.ok) {
            throw new Error('1. 중복확인 중 응답ok 아님...이유를 찾아봐라');
        }

        let result = await response.json();

        if (result === 0) {
            alert('사용가능한 아이디입니다.')
            isUsernameOk = true;
        } else {
            alert('이미 사용중인 아이디입니다. 다른 아이디를 등록해주세요.')
            isUsernameOk = false;
        }
    } catch (error) {
        alert('error')
        console.error('아이디 중복확인 오류:', error);
    }
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

    const pwck = document.querySelector('#pwck');
    const pwvalid = document.querySelector('#pwvalid');

    const msg = pwCheck(pw1);

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




// 전화번호에 숫자만 입력가능 알림창
function telCheck() {
    let tel = document.getElementById('tel').value;

    if (isNaN(tel)) {
        alert("숫자만 입력해주세요.");
    }
}

document.getElementById('tel').addEventListener('keyup',telCheck);




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



