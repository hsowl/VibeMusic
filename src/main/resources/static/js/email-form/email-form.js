// document.addEventListener("DOMContentLoaded", function() {
//     const emailForm = document.getElementById("emailForm");
//
//     emailForm.addEventListener("submit", function(event) {
//         event.preventDefault(); // 기본 제출 동작을 막음
//
//         // 폼 데이터 가져오기
//         const formData = new FormData(emailForm);
//
//         // 이메일을 서버로 전송
//         fetch("/send-email", {
//             method: "POST",
//             body: formData,
//         })
//             .then(response => response.json())
//             .then(data => {
//                 if (data.success) {
//                     alert("이메일이 성공적으로 발송되었습니다.");
//                 } else {
//                     alert("이메일 발송에 실패하였습니다.");
//                 }
//             })
//             .catch(error => {
//                 console.error("이메일 발송 중 오류 발생:", error);
//                 alert("이메일 발송 중 오류가 발생하였습니다.");
//             });
//     });
// });
fetch('https://script.google.com/macros/s/AKfycbzxN8GN14UcnQ2VQm4ZMUWygsPzxFh813cYjqmm3eCHSYQp2oLnSsYvI0bMg-QwRxE/exec', {
    method: 'POST',
    body: JSON.stringify(formDataObject),
    headers: {
        'Content-Type': 'application/json'
    }
})
    .then(response => response.json())
    .then(data => {
        if (data.result === 'success') {
            alert('양식이 성공적으로 제출되었습니다!');
            // 성공적으로 제출된 후 추가 동작을 수행할 수 있습니다.
        } else {
            alert('양식 제출 중 오류가 발생했습니다. 나중에 다시 시도해주세요.');
        }
    })
    .catch(error => {
        console.error('오류:', error);
        alert('오류가 발생했습니다. 나중에 다시 시도해주세요.');
    });