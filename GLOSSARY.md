# GLOSSARIO - Localfarm


Il seguente documento specifica il significato e l’utilizzo delle entità principali che sono emerse nel progetto; è diviso per aree di contesto.
E' stato deciso di realizzare questo file per facilitare la comprensibilità del progetto, dato che conta al suo attivo più di 150 classi.


## Prodotti

### Item
E’ una astrazione di un qualsiasi prodotto, racchiude quindi le informazioni minime necessarie che definiscono un articolo che la piattaforma presenta ai clienti.
Dato che più aziende possono vendere lo stesso prodotto, esso NON include le informazioni sul prezzo e quantità, esse sono definite nella sua relazione di vendita con le aziende, ovvero nello **Stock**. In altre parole, è la parte immutabile di un *flyweight* “prodotto acquistabile”.
Volendo fare un confronto con Amazon, esso rappresenta la scheda tecnica del prodotto e non una singola unità di questo.
Oltre ai suoi dettagli interni, racchiude anche informazioni sul suo stato, e un riferimento alla **Company** che lo produce.

### Stock
Rappresenta un lotto di un determinato **Item**, posseduto da una Company, in altre parole è la parte mutabile del *flyweight* descritto nel paragrafo precedente.
In quanto parte mutabile esso comprende tutti i campi che condividono questa proprietà, i quali si riconducono al prezzo unitario di un prodotto e la quantità di prodotti attualmente disponibili nel lotto.

### SimpleProduct
Rappresenta una concretizzazione di **Item**, ovvero un prodotto locale semplice, che oltre i dettagli base di un prodotto generico può contenere le Certification che un produttore inserisce per attestarne la qualità.

### Certification
Contiene i dettagli di una certificazione per i **SimpleProduct**, quali nome e descrizione.

### TransformedProduct
Rappresenta un prodotto di cui si possono indicare i passaggi della sua produzione, includendo anche le località e gli eventuali ingredienti coinvolti. 
Estende i dettagli di un **SimpleProduct**, e può quindi avere delle **Certification**.
Di suo, contiene una lista di **TransformationStep** che deve essere obbligatoriamente non vuota.

### TransformationStep
Descrive un passaggio di produzione di uno specifico TransformationProduct, definendone la località, la descrizione e gli eventuali ingredienti interni alla piattaforma (**SimpleProduct**).

### Bundle
Rappresenta un prodotto presentato come pacchetto di altri prodotti, ad esempio “i salami nostrani”, o “mix degustazione”.
E’ una concretizzazione di **Item**; di suo contiene una mappa che collega gli **Item** alla loro quantità presente nel pacchetto.

### ItemStatus
Definisce i 2 stati possibili di un **Item**:
- *awaitingReview*: Il prodotto esiste nella piattaforma, ma non è visibile pubblicamente poiché la sua richiesta di aggiunta deve essere accettata da un CURATORE.
In questo stato, ogni altra interazione che lo coinvolge non è valida. 
- *available*: Il prodotto è presente nella piattaforma, ed è visibile pubblicamente.
Il prodotto può essere inserito in degli Stock e di conseguenza può essere aggiunto al **Cart** e comperato.


## Eventi

### Event
Rappresenta un evento che è stato inserito all’interno della piattaforma, insieme a tutti i dettagli che riguardano la sua organizzazione.
Ad esso si possono iscrivere più **User** per parteciparvi e l’ORGANIZZATORE può invitare delle **Company** a presiedere, le quali possono poi accettare o rifiutare l’invito.
Può avere 3 stati:
- *planning*: l’evento è stato proposto, ma non è ancora pubblico
- *currentlyPublic*: l’evento è pubblico e non è ancora concluso
- *completed*: l’evento è concluso

### Invitation
Classe associativa tra **Event** e **Company**, essa rappresenta l’invito di una azienda a partecipare ad un determinato evento, il quale può essere accettato o rifiutato da questa.


## Richieste di verifica

### Submission
Rappresenta una richiesta di verifica generica che viene generata da una Company rispetto ad un'operazione che necessita di essere approvata da un CURATORE della piattaforma prima di apportare l’effettivo cambiamento determinata da essa.
In quanto richiesta generica questa non verrà utilizzata, lo saranno le sue specializzazioni.
Prima di essere approvata, un CURATORE sceglie di prenderla in carico.


### AddItemSubmission
Richiede l’inserimento di un **Item** all’interno della piattaforma, per far sì che possa essere utilizzato da se stesso ed altri trasformatori/distributori/venditori per le operazioni a loro disponibili.


### RecognizeItemSubmission
Richiede di aggiungere allo Stock indicato al suo interno la quantità di prodotto specificata.

## Sistema di acquisto

### Cart
Rappresenta il carrello di uno **User**, questa entità è in associazione 1-1 con lui.
Contiene al suo interno una collezione di ShoppingItem.

### ShoppingItem
Questa entità indica il prodotto all’interno del carrello di un utente, in quale quantità è stato indicato e da quale venditore si è provvisoriamente deciso di acquistarlo.
Per fare ciò contiene un riferimento allo **Stock** preso in considerazione, da cui deriva poi il prezzo.

### Order
Rappresentazione di un ordine effettuato da un certo **User** in un dato momento del tempo, contiene al suo interno una collezione di **OrderItem**.

### OrderItem
Indica un singolo dettaglio di un **Order**, ovvero quale **Item** è stato acquistato, per quale prezzo, in quale quantità e presso quale azienda.
Lui non può contenere un riferimento allo **Stock** poiché esso ha il prezzo mutabile, mentre quello relativo a quel momento non può esserlo per buon senso, per cui esso contiene direttamente l’**Item** di riferimento assieme alla **Company** che possiede lo stock da cui è stato acquistato.

### PaymentMethod
Rappresentazione di un metodo di pagamento online esterno alla piattaforma.
Esso dovrebbe fungere da interfaccia adattiva con cui avviare una procedura di pagamento, che poi verrà gestita interamente dal servizio di pagamento esterno in sé.


## Utenti e Aziende

### User
Rappresenta un utente qualsiasi, contiene quindi un nome, una mail, la sua password (hashata) e l’indirizzo di spedizione.
Inoltre contiene una lista di ruoli che può essere aggiornata nel tempo, l’idea è quella che la piattaforma concede i vari permessi, e possono essere sovrapposti.
I possibili ruoli sono:
- *BUYER*: semplice utente che può comperare.
- *CURATOR*: può vedere, prendere in carico ed accettare Submission.
- *ANIMATOR*: può organizzare eventi.
- *ADMIN*: gestisce i ruoli degli utenti e gli account aziendali.
Tutti gli utenti possono avere un **Cart**.
Un account di un utente può essere disattivato.

### Company
Descrive l’account di un’azienda, contiene quindi la mail, il nome, la password (hashata), e una sua descrizione.
Ogni azienda può aggiungere gli Item che produce, può inserire e modificare Stock di qualsiasi prodotto della piattaforma, e partecipare agli Event come invitata.
Un account di un’azienda può essere disattivato.