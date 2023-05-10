package shop.mtcoding.tstory.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.post.PostSaveReqDto;
import shop.mtcoding.tstory.dto.post.PostUpdateReqDto;
import shop.mtcoding.tstory.model.post.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
	
	private final PostRepository postRepository;

	@Transactional
    public void 게시글등록하기(PostSaveReqDto postSaveReqDto, Integer userId, MultipartFile file) throws Exception{
		
        int pos = file.getOriginalFilename().lastIndexOf(".");
		String extension = file.getOriginalFilename().substring(pos + 1);
		String filePath = "src/main/resources/static/img/";
		// 랜덤 키 생성
		String imgSaveName = UUID.randomUUID().toString();
		// 랜덤 키와 파일명을 합쳐 파일명 중복을 피함
		String imgName = imgSaveName + "." + extension;
		// 파일이 저장되는 폴더가 없으면 폴더를 생성
		File makeFileFolder = new File(filePath);
		if (!makeFileFolder.exists()) {
			if (!makeFileFolder.mkdir()) {
				throw new Exception("File.mkdir():Fail.");
			}
		}

		// 이미지 저장
		File dest = new File(filePath, imgName);
		try {
			Files.copy(file.getInputStream(), dest.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("사진저장 실패");
		}
		postSaveReqDto.setPostThumnail(imgName);
		postRepository.insertSave(postSaveReqDto);
	}

	@Transactional
    public void 게시글수정하기(PostUpdateReqDto postUpdateReqDto,Integer principalId,MultipartFile file) throws Exception {
		int pos = file.getOriginalFilename().lastIndexOf(".");
		String extension = file.getOriginalFilename().substring(pos + 1);
		String filePath = "src/main/resources/static/img/";
		// 랜덤 키 생성
		String imgSaveName = UUID.randomUUID().toString();
		// 랜덤 키와 파일명을 합쳐 파일명 중복을 피함
		String imgName = imgSaveName + "." + extension;
		// 파일이 저장되는 폴더가 없으면 폴더를 생성
		File makeFileFolder = new File(filePath);
		if (!makeFileFolder.exists()) {
			if (!makeFileFolder.mkdir()) {
				throw new Exception("File.mkdir():Fail.");
			}
		}

		// 이미지 저장
		File dest = new File(filePath, imgName);
		try {
			Files.copy(file.getInputStream(), dest.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("사진저장 실패");
		}
		postUpdateReqDto.setPostThumnail(imgName);
		postRepository.insertUpdate (postUpdateReqDto);
	}

    

	public void 썸네일없는게시글등록하기(PostSaveReqDto postSaveReqDto, Integer userId) {
		postRepository.insertSave(postSaveReqDto);
	}

    public void 원래썸네일없는게시글수정하기(PostUpdateReqDto postUpdateReqDto, Integer userId) {
		postRepository.insertNoUpdate(postUpdateReqDto);
	}

    public void 썸네일없는게시글로수정하기(PostUpdateReqDto postUpdateReqDto, Integer userId) {
		postRepository.updateNoThumnail(postUpdateReqDto.toModel(userId));
    }
}
    

