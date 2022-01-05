package damo.helper.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import damo.helper.common.FileHandlr;
import damo.helper.domain.Question;
import damo.helper.domain.QuestionFile;
import damo.helper.repository.QuestionFileRepository;
import damo.helper.repository.QuestionRepository;
import damo.helper.response.QuestionFileResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionFileService {

	private final QuestionFileRepository questionFileRepository;
	private final QuestionRepository questionRepository;

	@Transactional
	public void saveFiles(List<MultipartFile> files, Long questionId) {
		if(!files.get(0).isEmpty()) {
			Question question = questionRepository.findById(questionId).orElseThrow();
			deletePreviousFiles(question);
			
			for(MultipartFile file : files) {
				String filePath = FileHandlr.fileUpload(file, null);
				QuestionFile questionFile = QuestionFile.createFile(filePath, file.getOriginalFilename(), question);
				questionFileRepository.save(questionFile);
			}
		}
	}

	private void deletePreviousFiles(Question question) {
		List<QuestionFile> previousFiles = questionFileRepository.findByQuestion(question);
		if(previousFiles.size() > 0) {
			questionFileRepository.deleteAllById(previousFiles.stream().map(f->f.getId()).collect(Collectors.toList())); 
		}
	}

	public List<QuestionFileResponse> findByQuestion(Long questionId) {
		Question question = questionRepository.findById(questionId).orElseThrow();
		return questionFileRepository.findByQuestion(question).stream().map(f->new QuestionFileResponse(f)).collect(Collectors.toList());
	}
	
}
