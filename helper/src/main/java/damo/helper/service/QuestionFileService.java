package damo.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import damo.helper.common.FileHandlr;
import damo.helper.domain.Question;
import damo.helper.domain.QuestionFile;
import damo.helper.repository.QuestionFileRepository;
import damo.helper.repository.QuestionRepository;
import damo.helper.repository.jpa.QuestionFileJpaRepository;
import damo.helper.repository.jpa.QuestionJpaRepository;
import damo.helper.response.QuestionFileResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionFileService {

	private final QuestionFileRepository questionFileRepository;
	private final QuestionRepository questionRepository;
	//private final QuestionFileJpaRepository questionJpaFileRepository;
	//private final QuestionJpaRepository questionJpaRepository;
	
	@Transactional
	public void saveFiles(List<MultipartFile> files, Long questionId) {
		Question question = questionRepository.findById(questionId).orElseThrow();
		
		if(files.size() > 0 && !files.get(0).isEmpty()) {
			deletePreviousFiles(question);
		}
		
		for(MultipartFile file : files) {
			if(file.isEmpty()) {
				break;
			}
			String filePath = FileHandlr.fileUpload(file, null);
			QuestionFile questionFile = QuestionFile.createFile(filePath, file.getOriginalFilename(), question);
			questionFileRepository.save(questionFile);
		}
	}

	private void deletePreviousFiles(Question question) {
		List<QuestionFile> previousFiles = questionFileRepository.findByQuestion(question);
		if(previousFiles.size() > 0) {
			questionFileRepository.deleteAllById(previousFiles.stream().map(f->f.getId()).toList());
		}
	}

	public List<QuestionFileResponse> findByQuestion(Long questionId) {
		Question question = questionRepository.findById(questionId).orElseThrow();
		return questionFileRepository.findByQuestion(question).stream().map(f->new QuestionFileResponse(f)).toList();
	}
	
}
