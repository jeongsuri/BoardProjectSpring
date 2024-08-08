window.addEventListener("DOMContentLoaded", function() {
    const options = {
        center: {
            lat: 37.557756188912954,
            lng: 126.94062742683245,
        },
        marker: [
            {lat: 37.557756188912954, lng: 126.94062742683245},
            {lat: 37.557287959390024, lng: 126.94120499658828},
            {lat: 37.561184514897825, lng: 126.94069261563956},
        ],
        markerImage : "https://mblogthumb-phinf.pstatic.net/MjAyMjA3MjVfMTM4/MDAxNjU4NzMzODA5MDI0.B9qIqSRlaIQdx7G2YptOdHrc8HEdLfd7u3WdvI4Bf_wg._vlAr2eBB0JPYxunVLKz3qooV5nKSNo6DXWc2PzfZEsg.JPEG.djnice2503/SE-f8dd41c8-d2a9-4158-851b-c4f466c10840.jpg?type=w800"
    };
    mapLib.load("map1", 1000, 600, options);
    /*
    mapLib.load("map1", 300, 300, options);
    mapLib.load("map2", 400, 400, options);
    mapLib.load("map3", 500, 500, options);
    */
});