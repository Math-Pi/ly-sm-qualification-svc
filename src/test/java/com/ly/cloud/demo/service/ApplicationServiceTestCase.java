package com.ly.cloud.demo.service;




import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceTestCase {
    
//    @Resource
//    private ApplicationService applicationService;
//
//	@Test
//	public void getTest() {
//	    ApplicationDTO applicationDTO = applicationService.get("1");
//	    assertTrue(applicationDTO.getYymc().equals("本科生迎新"));
//	}
//	
//	//@FixMethodOrder(MethodSorters.JVM)这个注解不好用，所以把增删改方法写在一起
//    @Test
//    public void CUDTest(){
//        insertTest();
//        updateTest();
//        deleteTest();
//    }
//	
//	public void insertTest(){
//	    ApplicationDTO applicationDTO = new ApplicationDTO();
//	    applicationDTO.setYybh("3");
//	    applicationDTO.setYymc("3333");
//	    applicationDTO.setPxh(new BigDecimal(3));
//	    applicationDTO.setAgentid("10086");
//	    int result = applicationService.insert(applicationDTO);
//	    assertTrue(result==1);
//	}
//	
//    public void updateTest(){
//        ApplicationDTO applicationDTO = new ApplicationDTO();
//        applicationDTO.setYybh("3");
//        applicationDTO.setYymc("4444");
//        applicationDTO.setPxh(new BigDecimal(4));
//        applicationDTO.setAgentid("10033");
//        int result = applicationService.update(applicationDTO);
//        assertTrue(result==1);
//    }
//	
//    public void deleteTest(){
//        int result = applicationService.delete("3");
//        assertTrue(result==1);
//    }
//
//	@Test
//	public void queryPageTest(){
//	    ApplicationDTO applicationDTO = new ApplicationDTO();
//        PageInfo<ApplicationDTO> pageInfo = applicationService.queryPage(0, 0, applicationDTO );
//        List<ApplicationDTO> list = pageInfo.getList();
//        assertTrue(list.size()>0);
//	}
}
