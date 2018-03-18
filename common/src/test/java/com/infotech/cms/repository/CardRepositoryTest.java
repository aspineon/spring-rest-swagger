package com.infotech.cms.repository;

import com.infotech.accounting.domain.Account;
import com.infotech.accounting.domain.AccountType;
import com.infotech.accounting.domain.type.AccountState;
import com.infotech.accounting.service.AccountService;
import com.infotech.accounting.service.AccountTypeService;
import com.infotech.cms.domain.Card;
import com.infotech.cms.domain.CardHolder;
import com.infotech.cms.domain.CardHolderType;
import com.infotech.cms.domain.CardType;
import com.infotech.cms.domain.type.*;
import com.infotech.cms.service.CardHolderService;
import com.infotech.cms.service.CardHolderTypeService;
import com.infotech.cms.service.CardService;
import com.infotech.cms.service.CardTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * card issuer repo unit test.
 *
 * @author MohammadReza Alagheband
 */
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class CardRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    private static final Logger LOG = LoggerFactory.getLogger(CardRepositoryTest.class);


    @Autowired
    private CardRepository cardIssuerRepository;
    @Autowired
    private CardHolderService cardHolderService;
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CardTypeService cardTypeService;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardHolderTypeService cardHolderTypeService;
    @Autowired
    private DataSource ds;

    private JdbcTemplate jdbcTemplate;

    @BeforeClass
    public void setup() {
        jdbcTemplate = new JdbcTemplate(ds);

        jdbcTemplate.update("insert into card_holder_type (id,name) values (1, 'customer')");
        jdbcTemplate.update("insert into card_holder (id,address, card_holder_state, fk_card_holder_type, cellPhone, email," +
                        " enabled, first_name, last_name, national_id_number) " +
                        "values (1,'Tehran 2nd floor', 'ACTIVE', 1, '09123234566', 'customer@cardholder.com', 1, 'ali'," +
                        "'alavi', '0456667788')");
        jdbcTemplate.update("insert into account_type (id,name) values (1, 'customer')");
        jdbcTemplate.update("insert into account (id,account_number, account_state, fk_account_type, balance)" +
                " values (1,'12345678', 'READY', 1, 0)");
        jdbcTemplate.update("insert into card_type (id,name, code) values (1,'passenger', 1)");
        jdbcTemplate.update("insert into card (id,fk_account, fk_card_holder, card_state, fk_card_type, expiration_date, " +
                "issue_date_time, pan,pin_state, perso_state, technology,version,service_mask)" +
                " values (1, 1, 1, 'ACTIVE', 1,'2018-06-06', '2017-06-06 12:00:00', '1406200000000137','INACTIVE', 'NOT_PERSONALIZED', 'NORMAL',1,512)");

    }

    @AfterClass
    public void cleanup() {
        deleteFromTables("card","card_type","account","account_type","card_holder","card_holder_type");
    }

    @Test
    public void findByPanShouldSucceed() {
       Card card = cardRepository.findByPan("1406200000000137");

        Assert.assertNotNull(card.getCardType());
        Assert.assertNotNull(card);
    }

    @Test
    public void findByPanWithCardHolderShouldSucceed() {
        Card card = cardRepository.findByPanWithCardHolder("1406200000000137");

        Assert.assertNotNull(card.getCardHolder());
        Assert.assertNotNull(card);
    }

      

}
