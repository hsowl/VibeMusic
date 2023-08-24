async function getOne(no){
    const result = await axios.get(`/replies/list/${no}`);
    // console.log(result);

    return result
}

async function getList({no, page, size, goLast}){
    const  result = await axios.get(`/replies/list/${no}`, {params : {page, size}})

    if(goLast){
        const total = result.data.total;
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({no:no, page:lastPage, size:size})
    }
    return result.data;
}

async function addReply(replyObj){
    const response = await axios.post('/replies/',replyObj)
    return response.data
}

async function getReply(rno){
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

async function modifyReply(replyObj){
    const response = await axios.put(`/replies/${replyObj.no}`,replyObj)
    return response.data
}

async function removeReply(rno) {
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}