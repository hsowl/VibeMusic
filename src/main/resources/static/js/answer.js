async function getOne(qNo){
    const result = await axios.get(`/answers/list/${qNo}`);
    // console.log(result);

    return result
}

async function getList({qNo, page, size, goLast}){
    const  result = await axios.get(`/answers/list/${qNo}`, {params : {page, size}})

    if(goLast){
        const total = result.data.total;
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({qNo:qNo, page:lastPage, size:size})
    }
    return result.data;
}

async function addAnswer(answerObj){
    const response = await axios.post('/answers/',answerObj)
    return response.data
}

async function getAnswer(qno){
    const response = await axios.get(`/answers/${qNo}`)
    return response.data
}

async function modifyAnswer(answerObj){
    const response = await axios.put(`/answers/${answerObj.ano}`,answerObj)
    return response.data
}

async function removeAnswer(qNo) {
    const response = await axios.delete(`/replies/${qNo}`)
    return response.data
}