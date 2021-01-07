package com.ly.cloud.demo.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ly.cloud.ServiceApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ApplicationControllerTestCase {
    
//    @Autowired
//    private WebApplicationContext context;
//    
//    private MockMvc mockMvc;
//    
//    @Before
//    public void setupMockMvc() throws Exception {   
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();   
//    }
//    
//    public String wrapper(Object obj) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(obj);
//    }
//    
//    @Test
//    public void CUDTest() throws Exception{
//        testInsertApp();
//        testUpdateApp();
//        testDeleteApp();
//    }
//    
//    public void testInsertApp() throws Exception{
//        //构造添加的应用信息
//        ApplicationDTO applicationDTO = new ApplicationDTO();
//        applicationDTO.setYymc("string");
//        applicationDTO.setYybh("3");
//        applicationDTO.setPxh(new BigDecimal(3));
//        applicationDTO.setAgentid("10086");
//        //调用接口，传入添加的参数
//        mockMvc.perform(post("/application")
//            .contentType(MediaType.APPLICATION_JSON_UTF8)
//            .content(wrapper(applicationDTO)))
//        //判断返回值，是否达到预期
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//        //使用jsonPath解析返回值，判断具体内容
//            .andExpect(jsonPath("$.data", is(1)))
//            ;
//    }
//    
//    public void testUpdateApp() throws Exception{
//        //构造添加的应用信息
//        ApplicationDTO applicationDTO = new ApplicationDTO();
//        applicationDTO.setYybh("3");
//        applicationDTO.setYymc("string3");
//        applicationDTO.setPxh(new BigDecimal(4));
//        applicationDTO.setAgentid("100863");
//        //调用接口，传入添加的参数
//        mockMvc.perform(put("/application")
//            .contentType(MediaType.APPLICATION_JSON_UTF8)
//            .content(wrapper(applicationDTO)))
//        //判断返回值，是否达到预期
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//        //使用jsonPath解析返回值，判断具体内容
//            .andExpect(jsonPath("$.data", is(1)))
//            ;
//    }
//
//    public void testDeleteApp() throws Exception{
//        //调用接口，传入添加的参数
//        mockMvc.perform(delete("/application/3"))
//        //判断返回值，是否达到预期
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//        //使用jsonPath解析返回值，判断具体内容
//            .andExpect(jsonPath("$.data", is(1)))
//            ;
//    }
//    
//    @Test
//    public void testGetApps() throws Exception {
//        mockMvc.perform(get("/application?pageNum=1&pageSize=5"))  
//        .andExpect(status().isOk())  
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//        .andExpect(jsonPath("$.data", notNullValue()))
//        .andExpect(jsonPath("$.data.list", notNullValue()))
//        ;
//    }
//    
//    @Test
//    public void testGetAppById() throws Exception {  
//        mockMvc.perform(get("/application/1"))  
//            .andExpect(status().isOk())  
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))  
//            .andExpect(jsonPath("$.data", notNullValue()))
//            .andExpect(jsonPath("$.data.yybh", is("1")))
//            .andExpect(jsonPath("$.data.yymc", is("本科生迎新")))
//            .andExpect(jsonPath("$.data.pxh", is(1)))
//            .andExpect(jsonPath("$.data.agentid", is("1000003")))
//            ;
//    }
    
}
