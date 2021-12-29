package damo.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import damo.helper.common.FileUpload;
import damo.helper.domain.QuestionFile;
import damo.helper.repository.QuestionFileRepository;
import damo.helper.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionFileService {

	private final QuestionFileRepository questionFileRepository;
	private final QuestionRepository questionRepository;
	
	@Transactional
	public void saveFiles(List<MultipartFile> files, Long questionId) {
		
		for(MultipartFile file : files) {
			if(file.isEmpty()) {
				break;
			}
			String filePath = FileUpload.fileUpload(file, null);
			QuestionFile questionFile = QuestionFile.createFile(filePath, file.getOriginalFilename(), questionRepository.findOne(questionId));
			questionFileRepository.save(questionFile);
		}
	}
	
}
