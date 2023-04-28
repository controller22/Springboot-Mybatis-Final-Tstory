package shop.mtcoding.tstory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.post.PostSaveReqDto;
import shop.mtcoding.tstory.model.post.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
	
	private final PostRepository postRepository;

	@Transactional
    public void 게시글등록하기(PostSaveReqDto postSaveReqDto, Integer userId) {
		System.out.println("디버그 55 : "+postSaveReqDto.getPostTitle());
		System.out.println("디버그 55 : "+postSaveReqDto.getPostContent());
		
    //     int pos = file.getOriginalFilename().lastIndexOf(".");
	// 	String extension = file.getOriginalFilename().substring(pos + 1);
	// 	String filePath = "C:\\temp\\img\\";

	// 	// 랜덤 키 생성
	// 	String imgSaveName = UUID.randomUUID().toString();

	// 	// 랜덤 키와 파일명을 합쳐 파일명 중복을 피함
	// 	String imgName = imgSaveName + "." + extension;

	// 	// 파일이 저장되는 폴더가 없으면 폴더를 생성
	// 	File makeFileFolder = new File(filePath);
	// 	if (!makeFileFolder.exists()) {
	// 		if (!makeFileFolder.mkdir()) {
	// 			throw new Exception("File.mkdir():Fail.");
	// 		}
	// 	}

	// 	// 이미지 저장
	// 	File dest = new File(filePath, imgName);
	// 	try {
	// 		Files.copy(file.getInputStream(), dest.toPath());
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 		System.out.println("사진저장 실패");
	// 	}
	// 	postSaveDto.setPostThumnail(imgName);
		postRepository.insertSave(postSaveReqDto.getPostTitle(), postSaveReqDto.getPostContent(), userId);
		System.out.println("디버그11 ");
	// }

    }
}
    

