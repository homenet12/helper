package damo.helper.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import damo.helper.request.ManagerRequest;
import damo.helper.service.ManagerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ManagerRestController {

	private final ManagerService managerService;
	
	@PostMapping("/manager")
	public void regist(@RequestBody ManagerRequest manager) {
		managerService.save(manager);
		//managerService.save(null)
	}
}
