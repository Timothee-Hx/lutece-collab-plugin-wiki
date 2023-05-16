
--
-- Dumping data for table wiki_topic_version
--

INSERT INTO wiki_topic_version (id_topic_version, edit_comment, id_topic, lutece_user_id, date_edition, id_topic_version_previous, is_published)
VALUES (1,'',1,'admin','2014-05-31 14:42:56',7, false);


--
-- Dumping data for table wiki_topic
--

INSERT INTO wiki_topic (id_topic, namespace, page_name, page_view_role, page_edit_role, parent_page_name) 
VALUES (1, 0, 'home', 'none', 'none', '');

--
-- Dumping data for table wiki_image
--

INSERT INTO wiki_image (id_image, name, mime_type, file_value, id_topic, width, height) 
VALUES (1,'LUTECE logo','image/png',0x89504E470D0A1A0A0000000D49484452000001030000006E0802000000ACAEE1AC00001FE74944415478DAED5D097814C5B6EEEE99C94CF60059212401040261DF6447443615914544F6451645B8A02817BD208B5C54DCD91EA88022283EBDA0080802221802886109102010882109D9F764D6EEF74F1AC721D33D4B4FF74C7CB7FF8F8FAF7ABA525D55A7FE3AE754579F22870E1D4AC890F15F0FF291471EF1761D64C8F03E6426C8906186CC041932CC909920438619321364C8304366820C1966C84C9021C30C9909326498213341860C336426C8906186CC041932CC909920438619321364C830437226F8AB886A234133DE6EA8A7412A941A93514B10FF752D67F1B793BB984C50524487702A21940CF5231B68F03F515F43FA28CCDD51A2630AAA88422D5358CD649432A7B29952DDDFA7931C8152A8031AC4FB06452BD5C12A9F20FCAF540792A482614C465DB951576AD097E1FFEAB23B15455769A3CEDBF515192AC83DA246EEBE6403F33F02FFE347C8BDD82C71024287E86F9530A773E8B2BADA7A1198A056109D23A91E8DA8AE91949FCAA93F6118E24A2173328B3E954DE757FD5D29A150F90536681518D6C6BFDE0324A574AAE1B4A9B238ADBCE0727941AAC950E9ED16B8058DD22CF79E8DA82E9194AF53AD3773E372019D9485A9902EA8AE5B72778B09D080635B2986365560E2178C8BF9F4968BA6F492BAD52FF6A1520787371D1218DE8E24298145307459FEA5BCF41F0DDA626FB7C66504FA10E35A2B06C609973B847D3E97DE92628281E0EDD6DC834026502431A42935BEB5129DE23EA0228E64D0DB2F9BA04CBDDD218E1AAEF069D0B86F8398BE24E59CFAB3DF70DA5874E7D7828C9F6993DEDB2D730A0A8A78B4A9E299568A0031E40E1571E816BDE38AB1B40E984C4298D03E9C9AD15E1113448A5B153858FF7BD5B43BCD64A2BDDD2B3C088EE80855A05407895BAC515F9E9F7EB0E4EEEFDE6E9F03748A30CBBD51A0C872AF3210BBAE9ABE4B3379D7BD769909235B2826B75588DC1956B89047AF3E6544EFD429C00A8A68FE44BD860F4AF70830E1EEB5DD70B2BDDD566E3C1DAF189FE08611EC08C9B9F45BA78C980DBD05179880E13FBBA3727013A196B1D3C82C6396271AF3EA8C274D29D48D12C605D46F21F5832A8B6F665DFEA266EDB50E0116D10B9D9403622597FBAD526665A2D15B9EB4B34CF055128BBA2BA11F3D53AD121DF3C649E3F522EF9301CE71E3B653D401919E799CAE322F33655BDD71A3FD55C4AB3D946DC33C24F7222DB322D1E895E513A798A051126FF653350D91CE26E280DE442C3961482DF426194083B84ECF8BEE18D887515F713B79435D20839F8A78EB2155ACD80EA17D688DC46BC70D69C59E96BB534C78F941659F680FCD0AD628D632F38F18BDB5A04452CAD80E337D831A7BFED1DA8A9CDBC91B19DA9BDE1286FFE21ECAEE0DBD207718480B8E183CBCA0E498094F36574C6B27A1AB641F570B99C5C70D5E594D8A6A393224AAABB71A5E9A7B2E3BF56B6F3D1D1813AF9820A58B6C1F29F9F4D213469307E740074C681746ADE8A3A43CAA1E6BE3403ABDF19CA7D714421A768B6A31C29BCD2688DC1B7B8BEE9CF4CAA33B47524B7B2A4951E4AEF02104BD2DF92ECDF4E945CFADA4D96342B09A5C3F5015A476AD4465ABC15464029BA6EF5E36A61E74BF96EF9E31FE92C9AB17D46AF5BC79F32C979B376F2E2D2DB5CD366CD8B0F8F878367DFAF4E9E3C78FF315A809888AEB3487A41CCF88110D34037B4639DF90ECBCAAA3A7739DCCCC3074C6B9FFA92ECBB49FAD499326A3478F7658DA9E3D7BD2D2D29C796E035F72ED232A77DE9D914191CAF841546C372A3082F0F1238C5ABA2C97BE75D278E5005359E87C39AB4F1993B21CDB03102B84EB30DB575F7D9599C9DB99F69830A3BD62D8032EEB479FFE2F2A5A0E60D3A66B47F43FBF27BC47FF447E1533FBA0C1C0D327010101BB77EFB65C4E983021379763B4BDF6DA6B0F3DF4109BDEB56BD7279F7CC2F7B8987653FD9D5B336D1E1B3877424BE71B929A5EB6F1CBEBCEE7AF2AB995717EB3FD3C5DBB76FDF7BFFFEDB0A865CB962526263AF3D0E73B2A873415EA1E90A4AACB7865E7B1358E466D188EAF375ED9EF7C61772B99E70E1A1CDA48FDFBF77FF5D5571D96B670E1C20B172EF0569C8F09A1BEE4A6212A95EB1D22051380CDE74D3FDCE4D695E232C12F382EB6E32C276B253513803F2E6CA92CB637978BCB84487F72E32095422011489FC1AF2A9AF4E4BBED2A1380F5C9C683B71CA805699930A793C097681231A144C7CC3860D07171415C268006208393B5F20013601DDD4EDE602783B84C58D055D93F46200F941DC7A81E9C6C27830026145433B37EE435075848C884A80072032606410E93444C003EBB64FAF61A07154464028C229846CE57A95E904F9736F5AD7F896D18D0AE65089BAED69A0E27E558DF2D28D69F4B2D72B5E1772E6D2F2FB8C277D79A0946A3F18D37DEE0CC969A9A5A54E4E0D18D83C8758FA88439CA645094E699CD84D5E65CBAF016FDC759D39DF34C590E552F46D1F26153E639D3D543AE96FCF105D3DE1BF65C676B26545454BCF3CE3B9CD92E5DBAC4E940DEAB3F2713042B04424A2694EB8949FBF4B62BAA2232C12585C089DE9DC2C60C8D65D345A5FA65EB2EBADFF0EAF23BB77F5FCF77D79A090683E1D1471F15FC20B71442E767545D27582E4D374FE87F7A4B944FF68AB5CC94FD0686BF246B261417178F193346C05338988019E1F3C755C16A814B68D231015872C27821AF3615C4628242E5DFA2E76B847B6B87523001484B5A6DD49571DE128B093001B63FEE2378C9080A810C6EC4A6E99CCBBA1F5E234CA2BD195C74CCDE6E03A998D0A23EF94E7FE19BEF256502E71AB3584C088EECD430FE29376B28111372AEEF2EC93EC3794B2C26248492ABFB09943B15D65C3DEA03CBA576FB64A6B2409486B3F8E69AE9F34BBC0692544C18DF5AF1742BE12F1725654276857939B5D68F6231A151C2B8A0B0B66ED65022265414A666A67CCE794B2C264C69AB18D942A0DC553D672ADB0D67D374C14DDD37F38495C3878C5266EE615E0D2315133E18E0D6663B4999003C77D09055719FA214850924A968D16B09A574F13DA20D2462026D325C4F5CC1D01CEFDAC562C2FA41AAC642BFC2D14CDC4EFADF5B393026EF329CF95C583976F0EC0103DF467D4998505F436E7BCCADEF12A56602AC23D848D6BF88C204BF90A6B11D66B85F3D899800645EDC565174CDF677519810E14F7E3C44A8DC951ADF67BFB55CE9F62CA4EFA68AD56A0B369E331E48E75E4C9584096DC3A8557D9D0B54C003A99970F016BD3EF9BEA9511426D46BD83DB2C570F7AB271D13726FEC2BBAF3ABEDEFA230A14B24B5B49740B99381119AF15BFEBC62AA370F870A13ABD516ECBD61FAF80277B19230E1A118EAC5AE759A09BFE5D02B4F8ACF84B0260343631F76BF7AD231A130F344DE4D8ED752A23061601C35B7B340B95311F1EA11EFB269465FA9DD2264203A446296F9F34ECE5B923061440BC5D4B66EEDC5959A09378A99178FDEE73C89C284A896A342A2BAB85F3DE998509A7B3E3B7597EDEFA230C19DCF941571DD7D862C61D34C79AE76C734B19A6C8DD44266D1316EA75912264C6BA778B2799D6642919699B24F7C26346E3B25A0810BFB26F8201D132A8B6FFE7181E36D60ADDD16180AB6790E1D3A6467C72130BB83F2D16642DFA9B51AA2EA37974D4BB170C4E26E2533F347C74C6018A6A4A4C436CF9E3D7B76EEDC69A7FCDA4C58D84DD9B7B15B9F2949CD0413438CDCADB77EE3280A139A7499AB0968E87EF5A46382AE2A2FFDCCFBB6BF3BB3EFE8871F7EF8F0C30FED6458DC43D943E8E769CACE63555D27B269FACE79F33B3509A03711A3F7707FE7E0CCBE23FBBB8F095B262CEFADECE8DE67FB52330118B7575F61D527A230E181EE8B549A10F7EB261D138CFA8AB493AB6C7F178509ABFBA91242052EA1AA7ACF56B6B9F77940CD268B37C56A722D80097A2E9F591226BCDC4DD9A76EEB04BA4627D0E2EB84799A0017BEB9E183744CD057E5DF3CC3D199A230E155373E5956F5795E99F0189B36DD3C5EB3DD487C785A274C6FA7185EB7FD84622D33590A3FA1DD9480FA75DA4FA82A49CF38FFB1EDEFA278CCCF75540E15FA758EF9D39C2EE3D8349D99ACDBB744AC265B23B79299E1849F209AC73CB285624ADD5E3B4A2F61E61F9160ED287E74486467F7AB271D13CAF22E645DF9CAF677519830B695625C6B81728742805A60D3747E9AEEDBF96235D91A570B99573CB976D43F865A50B7DF279CBD4BAF4894E27DC2A0D0D8FEEE574F3A261465FE9A7B739FEDEFA2306170136A4E2781725734EDED3368319B66CA72B43B9F15ABC9D648CA328709E5BC250913DA87532BFBD46926FC749B5EFBBB04EF981BF5886CFE84FBD5938E097937F717669EB0FD5D1426748DA296F414FA662DAA8D7AF83DDF80D15568B73E2D5693ADB1EFA669D3790FBE630EF7233F195AA7F71DD97EB9260A135CFD5A8D0FD23181EFCB355198D028D0FCF9B2B08A9121D19AB19BEE5D304CF5A72309A3F841F0ED7CB926D55ED4F503558DDD0800283513E61E36D43A7E429CBDA894B245AFA594C2DD5311246202439BAE27AEE03C6641ACBDA89B87A822FD85C89D54076AA6FEE5C0E8F72D35658A1F017FF64143768507F7A212EEED53272466427E1533FD406DB749A3D1ECDDBBD772E90C1376ECD8B16DDBB65A19A2DB4C0A0C6DE5660D2562426551DA1F17B770DE128B09C282FAD480D44CF992D404B217C694EF0D899B0495C38BAC0A73B817BEBB5231C19D6F97088999C0170F6FCF9E3DFEFEFE6CFA85175EB8768D63F7F29A356B3A74E8C0A63FFAE8236BF2B01025EE9D444CB0130F4F2C26748CA096F716E82AA8FACE51B6BEF75CA6345BFBA508FBDBAD613F1E9E544CA048E20B37BE679594092B128D67EF726C52DFB46953D3A64DD9F4CA952B39E3DB7DFEF9E75151F7DE9D2D59B2E4D4A953B53228D541CD7B2C76B3861231E1C6E935866AEEC81462314149113B87F9680471818A4A500F7FDB72A9DBBB98CE12CD4102FE75DC78319F37D28B544C005EECAA7C48688C03E998506D2426FEC0FDBE7DC58A153D7AF460D367CE9C8121542B434242C2071FFCF5A1EDAC59B3D2D3D36DCB69D2798E2630DA9D4A4AC1045D656EFA6F1FF0DD1531B6C5A2EECA5E8D84C99DD44CD84A0684B1178CB64CF7ED7CA6DCD9B897F651A1272672C534B1404226C406931F0D1018F7463A26ECBA6ADA71995B453EFBECB34F3FFDD7E2DD962D5BE013D3F4BDCE8B89898112888B8B632F8D46E3A851A3AAAAAA6CCB090C6B139D30DE9D4A4AC184ECD45DA5B9E7F9EE8AC88407EA91EF3D2CD03056759FAAECF0577856BAF096E1C47A3AF71A9C7DF3B5C247193F90A92830659C76B5E42F2E9BBEBEEA6CBC23919900BCD44DD94FD006246B2610462D53CD1D9B84CE49D11F75812498189EFD51CF77FE5A6868283C60B5FAAFAF90EFDEBD7BE3C68DF2F2F2E8E8E8F8F87895EA2F01EFDEBD7BC306BEA872648D5A6824A0E12C44678259219CFD90E00FF7536BDF11E76A01F0FEFBEFFFFEBBE3259DC5DD953D04A905B25E63CD53EB88FBCFA566749574CE25521D40D68F23D5FE0262E095EACC9B2CB47663A5D7DA959D9797C7996DD5AA55A9A9BC9F95F232417018BCFB98C00F5366B2DE950D2A7C01F02C98366DDA33CF3CE3B09CEAEAEA499326716E6167E1E68B05D199603F001E2176344877C2E029DB3EA1EA652FA4AC0026380C8047481D1795101A094F0A26146BCD5F69E8EC76889F9FDFF2E5CB2DAB439CD06AB56BD7AE3D74C84140C2D80E33FD429AB8DA7016E232C1615054428258D9F3BB281F167ABEA0AAC77465FB917C775D6582334151090F3021D4975C3750E5E7A2DD280513D6251B0F390A986C6E0C490E1D3A74E6CC999615556B9C3D7B164E339FF1600DDFA098B88EB3AC437C3A0F3199C03019173EAE2AB9653F97E84C88F0379F9F206C11095034EBABEA36910CAEFDD9131C68FD91352E85BD78EF37E3B13F1CCB5D722600DDA2A8D77A2A5D5295645014E91BEC301BA3AB604AEE385320FA023DE27C057C7D7DE12247D7203030302B2BEB4E0D7272729C2FA441E33EE1CD84B89E81FECA0621F7DC15A389B973B74A40212CF26FFD549071D46136D01EED75982D3333B3A2A2C2C947F78AA6163DE8D6F6332A2A810A7B800C8C305BEE653974413A9D9B6AC7DBB185ED06333E040505356AE4D8B5CBC8C8E05C2661E1F89CB5F1098AA7E3BD76DED6ED52E6E59F1DD8451241949078825113F46EBB28117685616A5BC50837B61AB8891BC5E6EFF71DDA4522C23113E03CBDDECB7327315BA3D2402C3862B85BE99DD140297CE23ACD51FB877BFED1FAEA825BBFAFA7BD7A443945122BFB78EE24666B94E98805470DF99E3D9ADEA95368037D88771F16B83D4B304C0CB1EA24F71B658FC1C72F2CAED3F30AA5C6930FA54DBADBC91B7595E2BC967207C16AB3DCC3FD3C2B779A5896C811115D6A38C50420444D2EE9A56C5ECF439D526D24DE3A654CCEF5260D584027346E3B45A5A9E799C71974A59929DB741577BDDDEE7B68E04B2EE9A9F4D8A1F4B0025627D9DB58211D9C6502A056985FB779E0A8EA826A6645A2F176A9D74CE45A50FA0444B799E48123CAB515D999299FF19D93E02D6894C42B0F2ABB444A2EF7BC2A66F9AFC6CC722F1D44EF3C13881A9F018E949BA1C1ECE36609B332D158A4AD2B34B8D7704AD5A8F5D381A109D23DA2A2F06AD6952F694147174B0DF80C33DA2B1E6B26A1DCAF17312B4F1A4B755E93BB6B4C60D1AF3135A5AD027A53DCAAC040DC9F6EDA7ED9A4F5F431E4CE8124EB47F70E8B7D9812DB6D8063509071AC30F3F8BD2D3A75150FC75293DB28EA694496BB913647FFDD71C5A4F7C60AA105429840D4584A235B9857D904BF7FA985D3D9F4D61413DF474975070A957F58DC23210DBB9182DEBBD5064397DCFD3DFFD621A3DED9957EEF02E21ED55231A2B9C24724F570328BDE9662F2D6F2A035043281457D0D39B18DA27F0C45B9314DC01CDA72D194E20D274930E04687377DD4CD38AA954569B937F7EB2AEB8A73EC3C427DC9496D14300DDC39920EE6D0D614E3E502EF7380855B4C6011A4261E8CA27A34A23A84534AA7274A38C44959F4C96C3AA3CE78C6AE42A509090C6D131896E01714EBF439854C75E91F650597CAF32F1BB4C5CEFD491D45B09AECDE9084DCDB8751CE9F649E5EC22465D350059965754BEE2230C1023F15D135924A08A542FDCCAB6F983902FFFCF04D6732AF0815553305D5E633B3D0173975DE10721E4A9F4038D39AA068953A48E913AC52075B4EA9A28D3A83AED4A82F35E8CAB46577CA0B2E1BF5E5DEAEAFC8F0579983C498E5EE6B967B032BB9C3E52BAC660AB54C613571AB8603B975C010E284984CB005ACC910355969602A453B8FF4EF010A86B4CADF64A8AC9B6B415283957B8581A9FAFBC85D5A26C890F1770107136262629A356BE6ED8AC9902132CE9D3B67E70B2D0E268C1D3B76FAF4E9DEAEB60C1922C3E5EF136426C8F87F099909326498E13213E024B46AE56E50441932EA1A9292920A0B0BF9EECA6B47326498213341860C336426C890618640260C1E3C78E0C081818181C9C9C99F7EFAA9D1C8BD91FAC5175F0C0B0BD3E974C5C5C5C78F1F3F77EE1C6736F8E8EDDBB7B75C6ED8B0213333B3569ED6AD5B4F993265FBF6ED292929B87CFCF1C77BF5EAB578B1CB017DE1054D9B36AD71E3C678C48E1D3BAE5CE10EAA959090F0D4534FC5C4C45CBF7E1D0DCCCFCFE7CC3661C284A64D9BAE58B182BD7CE59557D0D28F3FE6381710B5459D699A2E2A2AC2437FFAE927BE4E037AF7EE3D64C8103CFDF4E9D3EBD7AFAF7557A954AE5CB9D27289BABDF79E6B2137D90E47054A4A4ACE9F3F7FF4E8518627EA44505010BAAB4D9B367979797BF6EC3973E60C67B6E6CD9B231B5A575A5A9A9E9EBE7FFF7EDB2812B5AACDE2A38F3EE28C3982CCCB972F47E77FF6D9677CAD60C700B269B5227CF02D8409E000445E5050505656867170E8D0A1356BD670E6DCB2654B78783844151D6D0EBBFBC61B6FFCF2CB2FB6D9D8930D2C217BDF7CF3CD5BB76A07F9419B972D5B0699AD5EBD1A97CF3FFFFC8811235013976AEEE3E3B36BD7AE8080806BD7AE8584841C3B768CF364D2B8B838480883E3F6EDDBF1F1F177EEDC79EEB9E7F47A8E7D13100386D4934F3EC95E7EF1C517B9B9B92FBDF4926DCE912347A210D02F222202D53871E284853FB6DDFBF2CB2F1B0C86B4B434FC8FB46D2BF6EDDB87CE8708708991849E71A91FD80E4727A3A524497EF9E59790946D36DC5AB76E1D4639BA0B1254ABD598DAAE5EBD6A9B938DB9849A040707FBF9F9DDBC7973CE9C3926D37D1F1CA854AAAD5BB72251BF7E7D0C743666236A929191615B20BA7DEDDAB57070415ABE56B06300703E7A8D1D086102E66C4C153366CCA8AEAE7EFDF5D77BF4E8317AF468CEDAA07F21CB59B366810F9B366D82F0264F9ECC2718FBC39A6502D4CB98316330DF086342DFBE7D972C5902A61D3972046246AD380381A1C218B87804248A510EA1724699275C67C2BC79F340F8B7DF7E1B2A0E9D8619B456368AA2302E41C2B973E7620641256D676B96090E8F58B6034B87438EAB56AD6AD1A2053AD37616872A78FFFDF7B76DDB06E5D9B06143CC1A870F1FE6D43F2C133021A26341DD0103068033ACF6B6C5BBEFBE0B9D6C3F8E2FDB5D488C1F3F9E2FCEA997990042635A85A264B5F6D0A143D166F426E657DBCC162620FDCF7FFE131D8441535959C927183BCF659940D41C08022D248C0918A3B03A1CF61DAA0DCB61E6CC994863044041F30D3B014C484D4DC57400B30AE5DBAABECE9D3B83A8F68FD116910948839010105BB15AD9A64F9F8E2979EAD4A9D08AB884D507E658C724B7C0C20488868D48C7778A05E11C13FEF5AF7F6186454BF98616E17526B46CD9121A1332802470D9AD5B37D4157DF4F5D75FDB66B66602BAA95DBB76B0EDF08B1DC1F081650244029300B38E3026B0C31192F8F1C71FED6443D3929292D02EA46112E092F34C06422813E0DEA039F82B5B5701E303CA164A038E045FF5C46502A40332CC9E3D1B0AB05636D413D93077B07A0914055131F7D956DB9A092039A80EA72E2B2B8BF3E9CE3061E7CE9D30C330BAE072F00536F73213D8498B353070093F01660F9FA1891FD189700FF05798603008A040F804C3CA15C6E58103076CF3B04CC0F43C69D224F4358427800970DF376EDCE8EBEB8B2987CF7D87097BA006163360F7EEDDB065E7CFE73870DB552660AC04060642E3A32B38A90545014F1DF6189C45BE56B04C80ED01AF0997502C972F5F76A91FD80EC764DFB66D5B8C5AD860E855589EB5B2612E8015877E662F172E5C3878F0604EA38E6502A4C35AF66C817C4F77C80488094CD8BC7973EFDEBD612EC250E4CCE66526F4E9D367E9D2A516DD57AF5E3D6883EFBEFB0E8AC23633988036B369388BF84356CF720A864DF39D85C13201C311263B1E87E70A600251A3D3DE79E71D880A1AFCE2458EF0BD3000BEFDF6DBEFBFFF1E1E9BA515E027A66ADBCCAE3201B3A942A1808C615E72AE1D41DD0D1A3468F8F0E1762278B24CB05CDA3D0E821BD61D5E5252B27AF5EAE4E464DB6C70122223232DB1F8596309CCC9CECEAE959365025A847904F501256C6D600B1C32A15FBF7E98AAD08D486030A03738972BBCCC04D61CB29CD8872661A0800C9C4B87B8A5D168E072E5E4E46016E45B37640583999EBDE4F4625926C0BAC03C010BF2ECD9B3C2980074ECD8116A0A9D8BD26C976B5973E8975F7E411EF61758ED705E5F78E105DBA25C65026C1ED41924FCEDB7DF38EBF68F7FFC030624DFF1A12C58264021B07A189C292F77ED3B38B6C3A1F4E08C422E7CAC83E68741FBD8638FB1D6115B3790C176CF02CB04B675D0BAFFF9CF7FEC3CDD211330C49FAC01A65D28A2050B165CBA7489339B3799C01E586631873A74E8008D8F347EB1CD6CED2738148C337E02C6AE56ABC59C0A7B003511C6044B697CEBBF308D609C40F048630AC725A64C78FCB6395D6502A67CE834D833103067C560544C9C38111DC2B7724F88ED27D801AADAB76F5F586BECB67EF6303B90C1D68EB2F8091897C1C1C168859DB7250E9900FBC2CFCF6FDAB469ACEDCDE7857A9909D1D1D15BB76E85938009039703060CC010B138D0B52005133087594EDA14CC0400AD808D04C7CEF6169400CCA171E3C6117FDAAC3FFFFC33E71905AFBFFE7AA74E9DA0BED9CB1D3B76C072B07D03405879CCDDBB7747C97C47E5B26B7168E037DF7CC357738F3101B51D366C18065C5A5A1A51B37A1E1B1B0B15619BD3C204F41BC6C35B6FBD75F8F061BE62ED33013A19A6231250741050484848626222E70B132F330195C3C8C0B3E1DB4169B247FD81BEB66606211913D8853FC27526A097D9F90CE62CA61968794EEB9F5D33817D5C5454C4CA988FEAEC58614DE7FAF5EB7FF5D557C78E1DE3E48C8509501A204C525212E79BB5060D1A40BB9E3F7F7ED1A2457CEF7D3DC604F6F50BBC85FDFBF7A3EB50B1AB57AF729ED96161026CB6EDDBB75B860767B1F69900271E661B5422BBC48C7AAA542ACEA55BEFBF599B3D7BF6A851A34E9D3A05F71786414646067EE1CCE91213E069B19770586DAD646B2660CC412A1445B9CA0438A39874615975E9D2A559B366A034FBD6B31660036098A6A7A7A38DECEB27A87BB0C23627BB767EFBF66DE48407054DC579E63971FF2A2A94068A9D3A752AE73AE3CA952BA137608FA5A4A48018B6C39D6502060A0843D49C36C9699A3AEC7087BD07130525D3348DC7B56CD992B58439CFE6B25E45C5A8C5FC68C7C0B3CF04F6CF2DEF37D8455E4EC78965026604765D1E2C6517D384410813C051B8F69016C6220601D27CEE9D4B4CB05C72BEE5B16602F1E7DAB6AB4CC07CB374E952285CA461E061B6B3357959A0EBE11A62222C2D2D7DFBEDB7F9840A0D397FFE7C76B10B5320CA8498394D646B263469D204AE0E8697F511D11660FCC1E8825B8FC22F5CB860EB51D45A3B8236864E76A91F9C6402DB63902FA61EBD5EFFFDF7DFC36CE3CC66CD848080004C31376EDCE05C31271C31017310E6A9279E7882ED4656EE281C066AAD9C2C132C977676FD3803E17B5121308D46C339535A002384A839FFD87E51C80652592E411E5BC58A6101065A6E296AC0B9B8661FF8AB8888088C6F3BCB7C965A614A76E65C36740572E6E7E7DBD90A8606A2404BFDD11624ECF48CBFBF3F5CCF9C9C1C4E1B0364B0A431673BEC61CE0E77BEF7C2C3C3A1796C5F895AC04A07D560CFC046F9F8852FBF4B776B956C0D760CB8D30FF73541DE952D4306213341860C16321364C8304366820C1966C84C9021C30C9909326498213341860C336426C8906186CC041932CC909920438619321364C8304366820C1966C84C9021C30C9909326498F17FFBAC3F4325E2F5CE0000000049454E44AE426082,1,500,500);

--
-- Dumping data for table wiki_topic_version_content
--

--
-- Dumping data for table `wiki_topic_version_content`
--
INSERT INTO wiki_topic_version_content (id_topic_version, locale, page_title, wiki_content) VALUES (1,'en','wiki : Home','{{{ jumbotron | Welcome to the Wiki | powered by Lutece | This Wiki aims to be a very simple collaborative tool fully integrated to your Lutece site }}}\r\n\r\n= Key features =\r\n\r\n* Use standard Wikicreole syntax\r\n* Provide a very simple and efficient Wiki editor with removable Help panel\r\n* Can be easily customized by macros to add new rendering features (such as Bootstrap Jumbotron !)\r\n* Fully integrated to Lutece platform :\r\n** Use MyLutece authentication and roles\r\n** Compatible with Extend plugin and all its modules (comment, rating, hits, opengraph...) \r\n** Support Lutece\'s avatar and pseudo features\r\n** Use the graphical theme of the site. It will change the same way as all the site when the theme is modified.\r\n** Code rendering skins can be managed into [quot;Site\'s properties[quot; \r\n\r\n== Quick start ==\r\n\r\nTo create a new page, enter a link into an existing page (the page name in lower case with only underscores as extra characters) such as the link below :\r\n\r\n[[my_new_page]]. \r\n\r\nThen click on the link that appears in red (to be created) and edit your new page. You\'re done !\r\n\r\nYou will have also to create some roles for managing editing and deleting permissions and affect them to your users depending on which MyLutece module you are using.\r\n  \r\n== Quick rendering samples ==\r\n\r\n//Just click on Edit button to see the code of all this samples. All the syntax is available clicking on the Help button located at the top right corner.//\r\n\r\nThis text use **bold** and //italics//.\r\n\r\nVarious labels can be created, ex : {{{ label | info | Note }}} {{{ label | warning | Caution }}} {{{ label | success | Awesome }}} ... and badges : {{{ badge | 256 }}}\r\n\r\nA sample table (using all bootstrap features) :\r\n\r\n|=URL|=Description|\r\n|[[http://fr.lutece.paris.fr|http://fr.luteceparis.fr]]|Lutece Official Site|\r\n|[[http://dev.lutece.paris.fr/site-demo|http://dev.luteceparis.fr/site-demo]]|Demo site|\r\n|[[http://dev.lutece.paris.fr/fr|http://dev.luteceparis.fr/fr]]|OSS Site|\r\n\r\nA block of code (using [quot;sunburst[quot; skin) :\r\n\r\n{{{ code | \r\n/* Hello World program */\r\n\r\n[hashmark;include[lt;stdio.h[gt;\r\n\r\nmain()\r\n{\r\n    printf([quot;Hello World[quot;);\r\n} \r\n}}}\r\n\r\nSome alert boxes :\r\n\r\n{{{ info | This is an info alert }}}\r\n{{{ warning | This is an warning alert }}}\r\n\r\nA table of content :\r\n\r\n!!!TOC!!!\r\n\r\nA fixed size internal image aligned to the right :\r\n\r\n{{ 1 | LUTECE logo |200||right }}\r\n\r\n Some awesome icons with different size {{{ icon | check-circle }}} {{{ icon | cloud fa-3x }}} {{{ icon | cog fa-5x }}}\r\n\r\n \r\n');
INSERT INTO wiki_topic_version_content (id_topic_version, locale, page_title, wiki_content) VALUES (1,'fr','wiki : Accueil','{{{ jumbotron | Bienvenue dans le Wiki | powered by Lutece | Cet outil se veut être un outil collaboratif très simple pour votre site Lutece }}}\r\n\r\n= Fonctionnalités principales =\r\n\r\n* Utilisation de la syntaxe standard Wikicreole\r\n* Editeur Wiki très simple avec panneau d\'aide escamotable\r\n* Possibilité de personnaliser des macros pour obtenir des rendus spécifiques (ex: classe Bootstrap Jumbotron !)\r\n* Entièrement intégré à la plate-forme Lutece :\r\n** Authentification MyLutece et gestion des rôles\r\n** Compatible avec le plugin Extend et tous ses modules (comment, rating, hits, opengraph...) \r\n** Support des avatars et des pseudos Lutece\r\n** Compatible avec les thèmes graphiques du site.\r\n** Le rendu des blocs de code est configurable dans [quot;Gestion des propriétés du site[quot; \r\n\r\n== Premiers pas ==\r\n\r\nPour créer une nouvelle page, entrer un lien dans une page existante (le nom technique doit être en minuscules et underscore) comme le lien ci dessous :\r\n\r\n[[my_new_page]]. \r\n\r\nEnsuite cliquer sur ce lien (qui apparait en rouge de manière à indiquer que la page n\'existe pas encore). Vous arrivez alors sur cette nouvelle page vierge. Saisissez son titre et son contenu, puis enregistrer. C\'est fait !\r\n\r\nVous aurez aussi à gérer les rôles de modification/suppression et les affecter aux utilisateurs.\r\n  \r\n== Quelques exemples de rendu graphique ==\r\n\r\n// Vous pouvez cliquer sur le bouton Edition pour voir le code source Wiki de tous ces exemples. Toute la syntaxe est disponible via le bouton d\'aide situé en haut à droite.//\r\n\r\nCe texte contient des mots en **gras** et en //italique//.\r\n\r\nDe nombreux types de label peuvent être créés, ex : {{{ label | info | Note }}} {{{ label | warning | Caution }}} {{{ label | success | Awesome }}} ... et aussi des badges : {{{ badge | 256 }}}\r\n\r\nVoici un exemple de tableau (utilisant aussi des styles Bootstrap) :\r\n\r\n|=URL|=Description|\r\n|[[http://fr.lutece.paris.fr|http://fr.luteceparis.fr]]|Lutece Official Site|\r\n|[[http://dev.lutece.paris.fr/site-demo|http://dev.luteceparis.fr/site-demo]]|Demo site|\r\n|[[http://dev.lutece.paris.fr/fr|http://dev.luteceparis.fr/fr]]|OSS Site|\r\n\r\nUn bloc de code (utilisant le thème [quot;sunburst[quot; ) :\r\n\r\n{{{ code | \r\n/* Hello World program */\r\n\r\n[hashmark;include[lt;stdio.h[gt;\r\n\r\nmain()\r\n{\r\n    printf([quot;Hello World[quot;);\r\n} \r\n}}}\r\n\r\nDes boîtes d\'alerte :\r\n\r\n{{{ info | This is an info alert }}}\r\n{{{ warning | This is an warning alert }}}\r\n\r\nUne table des matières :\r\n\r\n!!!TOC!!!\r\n\r\nUne image alignée à droite avec une largeur fixée à 200 pixels :\r\n\r\n{{ 1 | LUTECE logo |200||right }}\r\n\r\n Des icônes avec des tailles différentes {{{ icon | check-circle }}} {{{ icon | cloud fa-3x }}} {{{ icon | cog fa-5x }}} \r\n');
