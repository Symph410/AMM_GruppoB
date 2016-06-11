/**
 * Author:  sym410
 * Created: May 18, 2016
 */


INSERT INTO Saldo (id, conto)
    Values 
        (default, 100.00),
        (default, 200.00),
        (default, 27.50),
        (default, 170.50);

INSERT INTO Venditore (id, username, psw, saldo_id)
    Values
        (1, 'Seller1', 'psw1', 1),
        (2, 'Seller2', 'psw2', 2);

INSERT INTO Acquirente (id, username, psw, saldo_id)
    Values 
        (3, 'Buyer1', 'psw2', 3),
        (4, 'Buyer2', 'psw3', 4);


INSERT INTO ObjectSale (id, nome, url, quantity, description, price, category, venditore_id)
    VALUES 
        (default, 'Ugin foil 1st ed', 'DatiStatici/Images/ugin.jpg', 5,
           'Carta Ugin da FOE, condizione mint', 17.40, 'TCG', 1),
        
        (default, 'Bionicle lev kah', 'DatiStatici/Images/Bionicle.jpg', 10,
         'Bionicle prima edizione, 2005', 7.50, 'Action Figure', 1 ),

        (default, 'Nave in fiammiferi', 'DatiStatici/Images/caravella.jpg', 1,
         'Nave in fiammiferi fatta a mano', 50.00, 'Modellismo', 2),

        (default, 'Gundam Damashii', 'DatiStatici/Images/Gundam.jpg', 5,
         'Modellino gundam dashii, importato dal Giappone', 25.00, 'Action Figure', 1),

        (default, 'Portadeck swamp', 'DatiStatici/Images/protector.png', 10,
         'Porta carte in plastica rinforzata', 10.00, 'Gadget', 2);
    