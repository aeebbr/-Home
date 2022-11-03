// 로그아웃 버튼 클릭
const handleClickLogout = () => {
  fetch("/user/logout").then((res) => {
    if (res.status === 202) {
      location.href = "/";
      //   location.reload();
    }
  });
};
