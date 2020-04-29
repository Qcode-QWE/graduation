'use strict'
var localVideo = document.querySelector('video#localvideo');
var remoteVideo = document.querySelector('video#remotevideo');

var btnConn = document.querySelector('button#connserver');
var btnLeave = document.querySelector('button#leave');

var adminName = null;
var outputArea = document.querySelector('textarea#output');
var inputArea = document.querySelector('textarea#input');
var btnSend = document.querySelector('button#send');

var localStream = null;

var roomid = null;
var socket = null;

var state = 'init';

var pc = null;

btnSend.onclick = () => {
    var data = inputArea.value;
    data = adminName + ':' + data;
    socket.emit('text', roomid, data);
    inputArea.value = '';
}

function sendMessage(roomid, data) {
    console.log('send p2p message', roomid, data);
    if (socket) {
        socket.emit('message', roomid, data);
    }
}

function handleOfferError(err) {
    console.error('Failed to get Offer!', err);
}

function handleAnswerError(err) {
    console.error('Failed to get Answer!', err);
}

function getAnswer(desc) {
    pc.setLocalDescription(desc);
    sendMessage(roomid, desc);
}

function getOffer(desc) {
    pc.setLocalDescription(desc);
    sendMessage(roomid, desc);
}

function call() {
    if (state === 'joined_conn') {
        if (pc) {
            var options = {
                offerToReceiveAudio: 1,
                offerToReceiveVideo: 1
            }
            pc.createOffer(options)
                .then(getOffer)
                .catch(handleOfferError);
        }
    }
}

function conn() {
    socket = io.connect("https://chenjinpeng.top:443");

    socket.on('joined', (roomid, id) => {
        console.log('receive joined message:', roomid, id);
        state = 'joined';
        createPeerConnection();
        btnConn.disabled = true;
        btnLeave.disabled = false;

        console.log('receive joined message:state=', state);
    });

    socket.on('otherjoin', (roomid, id) => {
        console.log('receive otherjoin message:', roomid, id);

        if (state === 'joined_unbind') {
            createPeerConnection();
        }
        state = 'joined_conn';
        console.log('receive otherjoin message:state=', state);
        call();
    });

    socket.on('full', (roomid, id) => {
        console.log('receive full message:', roomid, id);
        state = 'leaved';
        console.log('receive full message:state=', state);
        socket.disconnect();
        alert('the room is full!');
    });

    socket.on('leaved', (roomid, id) => {
        console.log('receive leaved message:', roomid, id);
        state = 'leaved';
        console.log('receive leaved message:state=', state);
        socket.disconnect();

        btnConn.disabled = false;
        btnLeave.disbaled = true;
    });

    socket.on('bye', (roomid, id) => {
        console.log('receive bye message:', roomid, id);
        state = 'joined_unbind';
        closePeerConnection();
        console.log('receive bye message:state=', state);
    });

    socket.on('message', (roomid, data) => {
        console.log('receive message message:', roomid, data);

        if (data) {
            if (data.type === 'offer') {
                pc.setRemoteDescription(new RTCSessionDescription(data));
                pc.createAnswer()
                    .then(getAnswer)
                    .catch(handleAnswerError);
            } else if (data.type === 'answer') {
                pc.setRemoteDescription(new RTCSessionDescription(data));
            } else if (data.type === 'candidate') {
                var candidate = new RTCIceCandidate({
                    sdpMLineIndex: data.label,
                    candidate: data.candidate
                });

                pc.addIceCandidate(candidate);
            } else {
                console.error('the message is invalid!', data);
            }
        }
    });

    socket.on('text', (roomid, data) => {
        console.log('receive text message:', roomid, data);
        outputArea.value = outputArea.value + data + '\r';
    });

    socket.emit('join', roomid);

    btnConn.disabled = true;
    inputArea.disabled = false;
    btnSend.removeAttribute("disabled");
    return;

}

function getMediaStream(stream) {
    localStream = stream;
    localVideo.srcObject = localStream;

    conn();
}

function handleError(err) {
    if (err) {
        console.error('Failed to get Media Stream:', err);
    }
}

function start() {
    if (!navigator.mediaDevices ||
        !navigator.mediaDevices.getUserMedia) {
        console.error('ths getUserMedia is not support!');
        return;
    } else {
        var constraints = {
            video: true,
            audio: true
        }

        navigator.mediaDevices.getUserMedia(constraints)
            .then(getMediaStream)
            .catch(handleError);
    }
}

function connSignalServer(room, admin) {
    adminName = admin;
    roomid = room;
    start();
    return true;
}

function closeLocalMedia() {
    if (localStream && localStream.getTracks()) {
        localStream.getTracks().forEach((track) => {
            track.stop();
        });
    }
    localStream = null;
}

function leave() {
    if (socket) {
        socket.emit('leave', roomid);
    }
    closePeerConnection();
    closeLocalMedia();
    btnConn.disabled = false;
    btnLeave.disable = true;
    inputArea.disabled = true;
    btnSend.setAttribute("disabled", true);
}

function createPeerConnection() {
    console.log('create RTCPeerConnection!');
    if (!pc) {
        var pcConfig = {
            'iceServers': [{
                'urls': 'turn:114.116.238.215:3478',
                'credential': '123456',
                'username': 'root'
            }]
        }
        pc = new RTCPeerConnection(pcConfig);
        pc.onicecandidate = (e) => {
            if (e.candidate) {
                console.log('find an new candidate', e.candidate);
                sendMessage(roomid, {
                    type: 'candidate',
                    label: e.candidate.sdpMLineIndex,
                    id: e.candidate.sdpMid,
                    candidate: e.candidate.candidate
                })
            }
        }

        pc.ontrack = (e) => {
            remoteVideo.srcObject = e.streams[0];
        }
    }

    if (localStream) {
        localStream.getTracks().forEach((track) => {
            pc.addTrack(track, localStream);
        });
    }
}

function closePeerConnection() {
    console.log('close RTCPeerConnection!');
    if (pc) {
        pc.close();
        pc = null;
    }
}

btnLeave.onclick = leave;