import axios from 'axios';

export default {

  getImage(imageId) {
    return axios.get('/image/' + imageId, {
      responseType: 'arraybuffer'
    })
  },
  createImage(image) {
    let data = new FormData();
    data.append('image', image);
    let config = {
      header : {
        'Content-Type' : 'multipart/form-data'
      }
    }
    return axios.post('/image/create', data, config)
  },
  deleteImage(imageId) {
    return axios.delete('/image/delete/' + imageId)
  },
  addImageToProfile(imageId) {
    return axios.post('/image/profile/' + imageId, {
      responseType: 'arraybuffer'
    })
  },
  parseImg(res) {
    const base64 = btoa(
      new Uint8Array(res.data).reduce(
        (data, byte) => data + String.fromCharCode(byte),
        ''
      )
    );
    return base64;
  },
  getPlaceholder() {
    return "../img/placeholder.png"
  }

}