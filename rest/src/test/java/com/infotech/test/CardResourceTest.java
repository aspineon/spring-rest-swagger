package com.infotech.test;

import com.infotech.accounting.domain.Account;
import com.infotech.accounting.domain.AccountType;
import com.infotech.accounting.domain.type.AccountState;
import com.infotech.accounting.repository.AccountTypeRepository;
import com.infotech.accounting.service.AccountTypeService;
import com.infotech.cms.domain.Card;
import com.infotech.cms.domain.CardHolder;
import com.infotech.cms.domain.CardHolderType;
import com.infotech.cms.domain.CardType;
import com.infotech.cms.domain.dto.CardAssignmentRequest;
import com.infotech.cms.domain.dto.CardDto;
import com.infotech.cms.domain.type.*;
import com.infotech.cms.repository.CardHolderRepository;
import com.infotech.cms.repository.CardHolderTypeRepository;
import com.infotech.cms.repository.CardRepository;
import com.infotech.cms.repository.CardTypeRepository;
import com.infotech.cms.service.CardHolderService;
import com.infotech.cms.service.CardService;
import com.infotech.cms.service.CardTypeService;
import com.infotech.util.JacksonObjectMapper;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * test units provided for the Enrollment controller entity
 *
 * @author MohammadReza Alagheband
 */
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-test.xml",
        "classpath:spring/applicationContext-mvc.xml",
        "classpath:spring/testContext.xml"

})
public class CardResourceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardHolderRepository cardHolderRepository;

    @Autowired
    private CardHolderTypeRepository cardHolderTypeRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private CardTypeRepository cardTypeRepository;

    @Autowired
    private JacksonObjectMapper mapper;
    private MockMvc mockMvc;


    @BeforeMethod(alwaysRun = true)
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    @Test
    private void fetchCardByPanShouldSucceed() throws Exception {

        //arrange
        CardDto cardDto = new CardDto();
        cardDto.setTechnology(Technology.NORMAL);
        cardDto.setCardTypeId(1L);
        cardDto.setExpirationDate(LocalDate.now());


        CardType passenger = new CardType();
        passenger.setId(1L);
        passenger.setName("passenger");
        passenger.setCode(Byte.decode("0x00"));

        Card card = new Card();
        card.setCardType(passenger);
        card.setExpirationDate(LocalDate.now().plusYears(1));
        card.setPan("1406200000000137");
        card.setTechnology(Technology.NORMAL);

        Mockito.when(cardService.findByPan(anyString())).thenReturn(card);

        //act
        MockHttpServletRequestBuilder request = get("/api/v1/cards/1406200000000137");


        ResultActions response = mockMvc.perform(request.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        //assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("cardType.id").value(passenger.getId()))
                .andExpect(jsonPath("pan").value(card.getPan()))
                .andExpect(jsonPath("expirationDate", not(isEmptyOrNullString())))
                .andExpect(jsonPath("persoState").value("NOT_PERSONALIZED"))
                .andExpect(jsonPath("technology").value("NORMAL"))
                .andExpect(jsonPath("version",is(1)))
                .andExpect(jsonPath("serviceMask",is(card.getServiceMask())))
                .andDo(log());
    }

    @Test
    private void cardAssignmentShouldSucceed() throws Exception {

        //arrange

        CardAssignmentRequest cardDto = new CardAssignmentRequest();
        cardDto.setTechnology(Technology.NORMAL);
        cardDto.setCardTypeId(1L);
        cardDto.setExpirationDate(LocalDate.now());
        cardDto.setInitBalance(new BigDecimal("0"));
        cardDto.setAccountNumber("12345678");
        cardDto.setCardHolderId(1L);
        cardDto.setCardTypeId(1L);
        cardDto.setExpirationDate(LocalDate.now().plusYears(1));
        cardDto.setAccountTypeId(1L);
        cardDto.setTechnology(Technology.NORMAL);

        CardHolderType cardHolderType = new CardHolderType();
        cardHolderType.setName("Customer");
        cardHolderType.setId(1L);

        CardHolder cardHolder = new CardHolder();
        cardHolder.setAddress("Tehran 2nd floor");
        cardHolder.setEmail("cardholder@cms.com");
        cardHolder.setFirstName("Shahab");
        cardHolder.setLastName("Alagheband");
        cardHolder.setCellPhone("09121234567");
        cardHolder.setNationalIdNumber("0454445567");
        cardHolder.setCardHolderState(CardHolderState.ACTIVE);
        cardHolder.setCardHolderType(cardHolderType);

        AccountType accountType =new AccountType();
        accountType.setName("test");
        accountType.setId(1L);


        Account account = new Account();
        account.setAccountNumber("123456789");
        account.setAccountType(accountType);
        account.setAccountState(AccountState.READY);
        account.setBalance(new BigDecimal(1000));
        account.setAccountType(accountType);

        CardType passenger = new CardType();
        passenger.setId(1L);
        passenger.setName("passenger");
        passenger.setCode(Byte.decode("0x00"));

        Card card = new Card();
        card.setId(1L);
        card.setCardType(passenger);
        card.setExpirationDate(LocalDate.now().plusYears(1));
        card.setPan("1406200000000137");
        card.setTechnology(Technology.NORMAL);
        card.setPersoState(PersoState.NOT_PERSONALIZED);
        card.setIssueDateTime(LocalDateTime.now());
        card.setCardState(CardState.ACTIVE);
        card.setExpirationDate(LocalDate.now().plusYears(1));
        card.setPinState(PinState.INACTIVE);
        card.setCardHolder(cardHolder);
        card.setAccount(account);

        Mockito.when(cardService.assign(anyObject())).thenReturn(card);

        //act
        MockHttpServletRequestBuilder request = post("/api/v1/cards/assign")
                .content(mapper.writeValueAsString(cardDto));

        ResultActions response = mockMvc.perform(request.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        //assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("pan").value("1406200000000137"))
                .andExpect(jsonPath("expirationDate", not(isEmptyOrNullString())))
                .andExpect(jsonPath("persoState").value("NOT_PERSONALIZED"))
                .andExpect(jsonPath("technology").value("NORMAL"))
                .andDo(log());
    }

    @Test
    private void cardPersonalizationShouldSucceed() throws Exception {

        //arrange

        CardType passenger = new CardType();
        passenger.setId(1L);
        passenger.setName("passenger");
        passenger.setCode(Byte.decode("0x00"));

        Card card = new Card();
        card.setCardType(passenger);
        card.setExpirationDate(LocalDate.now().plusYears(1));
        card.setPan("1406200000000137");
        card.setTechnology(Technology.NORMAL);

        Mockito.when(cardService.personalizeCard(anyString())).thenReturn(card);

        //act
        MockHttpServletRequestBuilder request = get("/api/v1/cards/perso/1406200000000137");


        ResultActions response = mockMvc.perform(request.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        //assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("success").value(true))
                .andExpect(jsonPath("code").value(0))
                .andExpect(jsonPath("responseId", not(isEmptyOrNullString())))
                .andExpect(jsonPath("message").value("card with pan: 1406200000000137 personalized."))
                .andDo(log());
    }
}
