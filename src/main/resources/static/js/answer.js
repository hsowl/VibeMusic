async function getOne(qno){
    const result = await axios.get(`/answers/answerList/${qno}`);
    // console.log(result);

    return result
}

async function getList({qno, page, size, goLast}){
    const  result = await axios.get(`/answers/answerList/${qno}`, {params : {page, size}})

    if(goLast){
        const total = result.data.total;
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({qno:qno, page:lastPage, size:size})
    }
    return result.data;
}

async function addAnswer(answerObj){
    console.info("answerObj in answer.js :", answerObj.qno)
    const response = await axios.post('/answers/',answerObj)
    console.log("post 방식으로 댓글 설정 끝")
    return response.data
}


async function getAnswer(qno){
    const response = await axios.get(`/answers/${qno}`)
    return response.data
}

async function modifyAnswer(answerObj){
    const response = await axios.put(`/answers/${answerObj.ano}`,answerObj)
    return response.data
}

async function removeAnswer(qno) {
    const response = await axios.delete(`/answers/${qno}`)
    return response.data
}