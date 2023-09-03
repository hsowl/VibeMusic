
    const errors =[[${errors}]]
    console.log(errors)

    let errorMsg = ''

    if(errors){
    for (let i = 0; i< errors.length; i++){
    errorMsg += `${errors[i].field}은(는) ${errors[i].code} \n`
}
    history.replaceState({}, null, null)
    alert(errorMsg)

}


    //modify
    const link = [[${pageRequestDTO.getLink()}]]
    const formObj = document.querySelector("#f1")

    document.querySelector(".modBtn").addEventListener("click", function (e){
    e.preventDefault()
    e.stopPropagation()

    formObj.action = `/questionModify?${link}`
    formObj.method = 'post'
    formObj.submit()
},false)

    document.querySelector(".removeBtn").addEventListener("click",function (e){
    e.preventDefault()
    e.stopPropagation()

    formObj.action = `/questionRemove`
    formObj.method = 'post'
    formObj.submit()
},false)
