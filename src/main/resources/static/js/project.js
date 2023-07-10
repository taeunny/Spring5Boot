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

// joinme
let fzipbtn = document.querySelector("#findzipbtn");
let zipbtn = document.querySelector("#zipbtn");
let dong = document.querySelector("#dong");
let zipmodal = document.querySelector("#zipmodal");

let addrlist = document.querySelector("#addrlist");
let sendzip = document.querySelector("#sendzip");
let modal = null;       // 우편번호 모달

let email3 = document.querySelector("#email3");


zipbtn?.addEventListener('click', () => {
    while(addrlist.lastChild) {
        addrlist.removeChild(addrlist.lastChild);     // addrlist의 모든 자식요소를 지우는 것 (addr 가장 마지막 자식을 지우는것)
    }   // 이전 검색 결과 지움
    dong.value = '';    // 이전 검색 키워드 지움

    try {
        // 새로운 모달 창 생성
        modal = new bootstrap.Modal(zipmodal, {});
    } catch (e) { }

        modal.show();       // 모달창 띄우기
});

const showzipaddr = (jsons) => {
    jsons = JSON.parse(jsons);  // 문자열을 json객체로 변환
    let addr = '';
    jsons.forEach(function (data, idx){     // json 반복처리
        addr += `<option>${data['zipcode']} ${data['sido']}
                ${data['gugun']} ${data['dong']} ${data['bunji']}</option>`;
    });
    addrlist.innerHTML = addr;
};

fzipbtn?.addEventListener('click',()=> {
    if(dong.value === '') {
        alert('동이름을 입력하세요!!');
        return;
    }
    const url = '/join/zipcode?dong=' + dong.value;
    fetch(url).then(response => response.text())
            .then(text => showzipaddr(text));
});

sendzip?.addEventListener('click', () =>{
    let frm = document.forms.joinfrm;
    let addr = addrlist.value;      // 선택한 주소 항목
    if (addr !== '') {
        // 123-456 서울 관악구 신림동 (공백도 자르고, 앞에 숫자사이 -도 잘라서 각각 빈칸에 들어가게
        let zip = addr.split(' ')[0];      // 우편번호 추출
        let addrs = addr.split(' ');
        let vaddr =
            `${addrs[1]} ${addrs[2]} ${addrs[3]}`;   // 주소추출

        frm.zip1.value = zip.split('-')[0];     //[0] -> 첫번째꺼 추출
        frm.zip2.value = zip.split('-')[1];
        frm.addr1.value = vaddr;

        modal.hide();      // 모달창 닫음
    } else {
        alert('주소를 선택하세요!!');
    }
});

// 이메일 입력창
email3?.addEventListener('click', () => {
    let frm = document.forms.joinfrm;
    if(email3.value === '직접입력 하기') {
        frm.email2.readOnly = false;    // readonly 해제
        frm.email2.value = '';
    } else if (email3.value !== '선택하세요') {
        frm.email2.readOnly = true;
        frm.email2.value = email3.value;
    }
});

// 우편번호 검색 엔터키 입력 차단
dong?.addEventListener('keydown', (e) => {
    if(e.keyCode == 13)     // 엔터키가 입력되면 (엔터기값은 13)
       e.preventDefault();  // 이벤트 전파 방지
});

// 비밀번호 확인
let pwd = document.joinfrm.passwd;
let repwd = document.joinfrm.repasswd;
let pwdmsg = document.querySelector("#pwdmsg");
repwd?.addEventListener('blur', () => {
    let pmsg = '비밀번호가 서로 일치하지 않습니다!!';
    pwdmsg.className = 'text-danger';

    if (pwd.value === repwd.value) {
        pmsg = '비밀번호가 서로 일치합니다!!';
        pwdmsg.className = 'text-primary';
    }
    pwdmsg.innerText = pmsg;
});

// 아이디 중복 검사
let userid = document.joinfrm.userid;
let checkuid = document.joinfrm.checkuid;
let uidmsg = document.querySelector("#uidmsg");

const styleCheckuid = (chkuid) => {
    let umsg = '사용 불가능한 아이디입니다!!';
    uidmsg.className = 'text-danger';
    checkuid.value = 'no';

    if (chkuid === '0') {
        umsg = '사용 가능한 아이디입니다!!';
        uidmsg.className = 'text-primary';
        checkuid.value = 'yes';
    }
    uidmsg.innerText = umsg;
};
userid?.addEventListener('blur', () => {
    if (userid.value === '') {
        uidmsg.innerText = '6~16 자의 영문 소문자, 숫자와 특수기호(_)만 사용할 수 있습니다';
        uidmsg.className = 'text-warning';
        checkuid.value = 'no';
        return;
    }
    const url = '/join/checkuid/' + userid.value;
    fetch(url).then(response => response.text())
        .then(text => styleCheckuid(text));
});

