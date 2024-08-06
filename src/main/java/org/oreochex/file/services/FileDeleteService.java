package org.oreochex.file.services;

import lombok.RequiredArgsConstructor;
import org.oreochex.file.constants.FileStatus;
import org.oreochex.file.entities.FileInfo;
import org.oreochex.file.repositories.FileInfoRepository;
import org.oreochex.global.exceptions.UnAuthorizedException;
import org.oreochex.member.MemberUtil;
import org.oreochex.member.entities.Member;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDeleteService {
    private final FileInfoService infoService;
    private final FileInfoRepository infoRepository;
    private final MemberUtil memberUtil;

    //낱개 삭제
    public FileInfo delete(Long seq){
        FileInfo data = infoService.get(seq);
        String filePath = data.getFilePath();
        String createdBy = data.getCreatedBy(); // 업로드한 회원 이메일

        Member member = memberUtil.getMember(); //로그인한 계정 가져오기
        if(!memberUtil.isAdmin() && StringUtils.hasText(createdBy) && memberUtil.isLogin() && !member.getEmail().equals(createdBy)){
            throw new UnAuthorizedException();
        }
        // 1. 파일 삭제 (항상 파일이 존재하는지 체크하고 서비스를 해야함)
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }

        // 2. 파일 정보 삭제
        infoRepository.delete(data);
        infoRepository.flush();
        return data;
    }

    //여러개 삭세
    public List<FileInfo> delete(String gid, String location, FileStatus status){
        List<FileInfo> items = infoService.getList(gid, location, status);
        items.forEach(i -> delete(i.getSeq()));
        return items;
    }


    //모든 파일 삭제
    public List<FileInfo> delete(String gid, String location){
        return delete(gid, location, FileStatus.ALL);
    }

    public List<FileInfo> delete(String gid){
        return delete(gid, null);
    }
}
