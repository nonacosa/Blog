import axios from 'axios'

let Api = Function()

Api.prototype = {
    getBlog(page, fn) {
        axios.get(`/v1/getListByLastTime?src=web&pageNum=${page}`).then(res => {
            // if (res.data.code === 200) {
            fn(res.data)
            // }
        }).error()

    }
}
export default new Api()


