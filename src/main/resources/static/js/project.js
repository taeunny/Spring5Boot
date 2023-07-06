// agree
let agree1 = document.querySelector("#agree1");
let agree2 = document.querySelector("#agree2");
let okagree = document.querySelector("#okagree");
let noagree = document.querySelector("#noagree");

okagree?.addEventListener('click', () => {
    if (!agree1.checked) alert('이용약관 동의에 체크하세요!');
    else if (!agree2.checked) alert('개인정보 이용 동의에 체크하세요!');
    else location.href='/join/checkme';
});
noagree?.addEventListener('click', () => {
    location.href = "/";
});

// checkme
let frm = document.forms.agreefrm2;
let checkbtn2 = document.querySelector("#checkbtn2");
let canclebtn2 = document.querySelector("#canclebtn2");

checkbtn2?.addEventListener('click', () => {
    if (frm.name.value === '') alert('이름을 입력해주세요!');
    else if (frm.jumin1.value === '') alert('생년월일을 작성해주세요!');
    else if (frm.jumin2.value === '') alert('주민번호 뒷자리를 작성해주세요!');
    else if (!frm.infoagree.checked) alert('주민번호 처리에 동의하세요!');
    else {
       frm.method = 'post';
       frm.submit();
    }
});
canclebtn2?.addEventListener('click', () => {
    location.href = "/";
});

