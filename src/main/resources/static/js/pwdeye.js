function togglePasswordVisibility() {
    var $passwordInput = $('#pwd'); // ID를 사용하여 비밀번호 입력란을 선택
    if ($passwordInput.attr('type') === 'password') {
        $('.eyes').removeClass('fa-eye').addClass('fa-eye-slash');
        $passwordInput.attr('type', 'text');
    } else {
        $('.eyes').removeClass('fa-eye-slash').addClass('fa-eye');
        $passwordInput.attr('type', 'password');
    }
}
