/**
 * 파일 업로드, 삭제, 조회 공통 기능
 */

const fileManager = {
    /**
     * 파일 업로드
     */
    upload(files, gid, location){
        try{
            if(!files || files.length == 0){
                throw new Error("파일을 선택하세요.");
            }

            if(!gid || !gid.trim()){
                throw new Error("필수 항목 누락입니다(gid).");
            }

            const formData = new FormData();
            formData.append("gid", gid.trim());
            for(const file of files){
                formData.append("file", file);
            }

            if(location && location.trim()){
                formData.append("location", location.trim());
            }
        }catch(e){
            console.error(e);
            alert(e.message);
        }
    },

    /**
     * 파일 삭제
     */
    delete(){

    },

    /**
     * 파일 조회
     */
    search(){

    }
};

window.addEventListener("DOMContentLoaded", function(){
   //  파일 업로드 버튼 이벤트 처리
   const fileUploads = document.getElementsByClassName("fileUploads");
   const fileEl = document.getElementById("input");
   fileEl.type='file';
   fileEl.multiple = true;


   for(const el of fileUploads){
       el.addEventListener("click", function(){
           fileEl.files = "";
           delete fileEl.gid;
           delete fileEl.location;
           const dataset = this.dataset;
           fileEl.gid = dataset.gid;
           if(dataset.location) fileEl.location = dataset.location;
           fileEl.click();

       });
   }
   //파일 업로드 버튼 이벤트 처리 E

    //파일 업로드 처리
    fileEL.addEventListener("change", function(e){
        const files = e.target.files;
        fileManager.upload(files, fileEl.gid, fileEl.location);
    })

   // 파일 삭제 버튼 이벤트 처리

    // 파일 조회 버튼 이벤트 처리
});