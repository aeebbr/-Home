// 로그인 버튼 클릭
const handleClickLogin = () => {
  const idValidEl = document.getElementById("id-login-valid");
  const passwordValidEl = document.getElementById("password-login-valid");

  const id = document.getElementById("login-id").value;
  const password = document.getElementById("login-password").value;

  if (id && password) {
    formData = new FormData();
    formData.append("userid", id);
    formData.append("userpwd", password);

    fetch(`/user/login`, {
      method: "POST",
      body: formData,
    }).then((res) => {
      if (res.status === 202) {
        location.href = "/";
      }
    });
  }
  if (!id) {
    idValidEl.innerText = "아이디를 입력하세요";
  }
  if (!password) {
    passwordValidEl.innerText = "비밀번호를 입력하세요";
  }
};

const loginFailed = document.querySelector(".login-failed").innerText;

const openLogin = () => {
  const loginNav = document.querySelector("#nav-login");

  loginNav.click();
};

// 로그인 실패 문구가 존재한다면 모달 open
if (loginFailed !== "") {
  openLogin();
}

// 로그인 모달 닫기
const handleClickloginCancle = () => {

  document.querySelector("#btn-login-close").click();

  idValidEl.innerText = "";
  passwordValidEl.innerText = "";
  window.location.href = "/";
};

var infoModal = new bootstrap.Modal(document.getElementById("info-modal"), {
  keyboard: true,
  backdrop: true,
});

const handleClickInfo = () => {
  fetch(`user/info`)
    .then((res) => res.json())
    .then((data) => {
      document.querySelector("#info-id").innerText = data.userId;
      document.querySelector("#info-name").innerText = data.userName;
      document.querySelector("#info-password").innerText = data.userPwd;
      document.querySelector("#info-address").innerText = data.userAddr;
      document.querySelector("#info-phone-number").innerText = data.userPhoneNum;

      setModal(false);
    });
};

// 모달 세팅
// 회원 정보 모달이 열리는 경우는 두 가지
// 1) 일반 회원 정보 모달 open
// 2) 회원 정보 수정 클릭 후, 수정된 회원 정보 모달 open
const setModal = (isModified) => {
  const infoNav = document.querySelector("#nav-info");

  if (!isModified) {
    infoNav.setAttribute("data-bs-toggle", "modal");
    infoNav.setAttribute("data-bs-target", "#info-modal");
  } else {
    infoNav.removeAttribute("onclick");
  }
};

// 회원 정보 모달 닫기
const handleClickInfoClose = () => {
  document.querySelector("#btn-close-info").click();
  window.location.href = "/";
};

// 회원 정보 수정
const handleClickOpenModi = () => {
  const info = document.querySelector("#info-container");
  const title = document.querySelector("#info-modal h4");

  const id = document.querySelector("#info-id").innerText;
  const pw = document.querySelector("#info-password").innerText;
  const name = document.querySelector("#info-name").innerText;
  const addr = document.querySelector("#info-address").innerText;
  const pNumber = document.querySelector("#info-phone-number").innerText;

  title.innerText = "회원 정보 수정";

  const infoELs = document.querySelectorAll(".info");
  infoELs.forEach((element) => {
    element.setAttribute("style", "display: none;");
  });

  const infoInputELs = document.querySelectorAll(".info-input");
  infoInputELs.forEach((element) => {
    element.setAttribute("style", "display: display;");
  });

  document.querySelector("#btn-info-modi").setAttribute("style", "display: none;");
  document.querySelector("#btn-info-del").setAttribute("style", "display: none;");
  document.querySelector("#btn-info-check").setAttribute("style", "display: none;");

  console.log(document.querySelector("#btn-sign-up-cancle"));
  document.querySelector("#btn-sign-up-cancle").setAttribute("style", "display: display;");
  document.querySelector("#btn-info-modi-confirm").setAttribute("style", "display: display;");

  document.querySelector("#password-modi").setAttribute("value", pw);
  document.querySelector("#name-modi").setAttribute("value", name);
  document.querySelector("#address-modi").setAttribute("value", addr);
  document.querySelector("#phone-number-modi").setAttribute("value", pNumber);

  // info.innerHTML = `
  // <div class="modal-body" id="user-form">
  //           <form id ="form-modify" method="post" action="/user/modify">
  //             <div class="row">
  //               <div class="row mb-3">
  //                 <div class="col-md-3">
  //                   <label for="id-modi" class="form-label">아이디</label>
  //                 </div>
  //                 <div class="col-md-9">
  //                   <input type="text" class="form-control" id="id-modi" name="id" value="${id}" placeholder="아이디를 입력해 주세요"  readonly="readonly"/>
  //                   <div class="input-valid" id="id-info-valid"></div>
  //                 </div>
  //               </div>
  //             </div>
  //             <div class="row">
  //               <div class="row mb-3">
  //                 <div class="col-md-3">
  //                   <label for="password-modi" class="form-label">비밀번호</label>
  //                 </div>
  //                 <div class="col-md-9">
  //                   <input type="password" class="form-control" id="password-modi" name="pw" value="${pw}"  placeholder="영문 숫자 포함 6자리 이상" />
  //                   <div class="input-valid" id="password-info-valid"></div>
  //                 </div>
  //               </div>
  //             </div>
  //             <div class="row">
  //               <div class="row mb-3">
  //                 <div class="col-md-3">
  //                   <label for="name-modi" class="form-label">이름</label>
  //                 </div>
  //                 <div class="col-md-9">
  //                   <input type="text" class="form-control" id="name-modi" name="name" value="${name}" placeholder="이름을 입력해 주세요" />
  //                   <div class="input-valid" id="name-info-valid"></div>
  //                 </div>
  //               </div>
  //             </div>
  //             <div class="row">
  //               <div class="row mb-3">
  //                 <div class="col-md-3">
  //                   <label for="address-modi" class="form-label">주소</label>
  //                 </div>
  //                 <div class="col-md-9">
  //                   <input type="text" class="form-control" id="address-modi" name="addr" value="${addr}" placeholder="주소를 입력해 주세요" />
  //                   <div class="input-valid" id="address-info-valid"></div>
  //                 </div>
  //               </div>
  //             </div>
  //             <div class="row">
  //               <div class="row mb-3">
  //                 <div class="col-md-3">
  //                   <label for="phone-number-modi" class="form-label">전화번호</label>
  //                 </div>
  //                 <div class="col-md-9">
  //                   <input type="text" class="form-control" id="phone-number-modi" name="pNumber" value="${pNumber}" placeholder="010-xxxx-xxxx" />
  //                   <div class="input-valid" id="phone-number-info-valid"></div>
  //                 </div>
  //               </div>
  //             </div>
  //           </form>
  //         </div>
  // `;
};

// 회원 정보 수정 클릭
const handleClickInfoModi = () => {
  const passwordValidEl = document.getElementById("password-info-valid");
  const nameValidEl = document.getElementById("name-info-valid");
  const addressValidEl = document.getElementById("address-info-valid");
  const phoneNumberValidEl = document.getElementById("phone-number-info-valid");

  passwordValidEl.innerText = "";
  nameValidEl.innerText = "";
  addressValidEl.innerText = "";
  phoneNumberValidEl.innerText = "";

  const id = document.querySelector("#info-id").innerText;
  const password = document.getElementById("password-modi").value;
  const name = document.getElementById("name-modi").value;
  const address = document.getElementById("address-modi").value;
  const phoneNumber = document.getElementById("phone-number-modi").value;

  if (password && name && address && phoneNumber) {
    formData = new FormData();
    formData.append("userid", id);
    formData.append("userpwd", password);
    formData.append("username", name);
    formData.append("useraddr", address);
    formData.append("userphonenumber", phoneNumber);

    fetch(`/user/modify`, {
      method: "POST",
      body: formData,
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        // 회원 정보 display: display
        document.querySelector("#info-password").innerText = data.userphonenumber;
        document.querySelector("#info-name").innerText = data.username;
        document.querySelector("#info-address").innerText = data.useraddr;
        document.querySelector("#info-phone-number").innerText = data.userphonenumber;

        const infoELs = document.querySelectorAll(".info");
        infoELs.forEach((element) => {
          element.setAttribute("style", "display: display;");
        });

        const infoInputELs = document.querySelectorAll(".info-input");
        infoInputELs.forEach((element) => {
          element.setAttribute("style", "display: none;");
        });

        document.querySelector("#btn-info-modi").setAttribute("style", "display: display;");
        document.querySelector("#btn-info-del").setAttribute("style", "display: display;");
        document.querySelector("#btn-info-check").setAttribute("style", "display: display;");

        document.querySelector("#btn-info-modi-confirm").setAttribute("style", "display: none;");
        document.querySelector("#btn-sign-up-cancle").setAttribute("style", "display: none;");

        const id = data.userId;
        console.log(id);
      });

    // location.href = "/whereismyhome_be/user/modify";
  }

  if (!password) {
    passwordValidEl.innerText = "비밀번호를 입력하세요";
  }
  if (!name) {
    nameValidEl.innerText = "이름을 입력하세요";
  }
  if (!address) {
    addressValidEl.innerText = "주소를 입력하세요";
  }
  if (!phoneNumber) {
    phoneNumberValidEl.innerText = "전화번호를 입력하세요";
  }
};

// 회원 탈퇴 클릭
const handleClickDel = () => {
  if (window.confirm("탈퇴하시겠습니까?")) {
    fetch("/user/delete").then((res) => {
      if (res.status === 202) {
        location.href = `/`;
      }
    });
    window.location.href = "/user/delete";
  }
};
