## Programming Assignments
#### Assignment-1 (Mod3) passes the test
#### Assignment-2 (Mod4) passes the test
#### Assignment-3 (Mod5) passes the test

------------------------------------------------------


# Bitcoin-and-Cryptocurrency-Technologies

| Information | Week |
|---|---|
| Crypto and Cryptocurrencies | [Week 1](https://github.com/Amitha353/Bitcoin-and-Cryptocurrency-Technologies/blob/master/Week%201%20:%20%20Crypto%20and%20Cryptocurrencies) |
| Bitcoin achieves Decentralization | [Week 2](https://github.com/Amitha353/Bitcoin-and-Cryptocurrency-Technologies/blob/master/Week%202%20:%20%20How%20Bitcoin%20Achieves%20Decentralization) |
| Bitcoin Mechanism | [Week 3](https://github.com/Amitha353/Bitcoin-and-Cryptocurrency-Technologies/blob/master/Week%203%20:%20Mechanics%20of%20Bitcoin) |
| Store and Use Bitcoin | [Week 4](https://github.com/Amitha353/Bitcoin-and-Cryptocurrency-Technologies/blob/master/Week%204%20:%20How%20to%20store%20and%20Use%20Bitcoins(Secret%20Keys)) |
| Bitcoin Mining | [Week 5](https://github.com/Amitha353/Bitcoin-and-Cryptocurrency-Technologies/blob/master/Week%205%20:%20Bitcoin%20Mining) |
| Bitcoin Anonymity | [Week 6](https://github.com/Amitha353/Bitcoin-and-Cryptocurrency-Technologies/blob/master/Week%206%20:%20Bitcoin%20and%20Anonymity) |
| Community, Politics and Regulations | [Week 7](https://github.com/Amitha353/Bitcoin-and-Cryptocurrency-Technologies/blob/master/Week%207%20:%20Community%2C%20Politics%2C%20and%20Regulation) |
 
------------------------------------------------------
## 1. Crypto and Cryptocurrencies
------------------------------------------------------
* Cryptographic Hash function - mathematical function and takes three attributes: takes any string as input; fixed-size output(256 bits); efficiently computable;
#### Hash functions need to be cryptographically secure (Security properties):
 1. Collision-free - If x != y , then we can assume H(x) != H(Y);
 2. Hiding- Given H(x), infeasible to find x.For common value x, Take x and concatenate it with a value r.
     (com, key) := commit(msg)
     match := verify(com, key, msg)
 3. Puzzle-friendly - For every possible output value y, if k is chosen from a distribution with high min-entropy(widely spread out distribution), then it is infeasible to find x such that H(k | x) = y; 

#### SHA-256 hash function
* Takes the message (256 bits) being hashed and breaks it into blocks of 512 bits long;
* Since the message is not necessarily gonna be a multiple of the block-size some padding needs to be appended.
* The padding consist of - 64 bit field at end - before that 1 bit - followed by some 0 bits(until 512 msg block - rest chopped off;

#### Hash Pointer - Data structure - pointer to where information is stored and where a cryptographic hash of the information can be stored.
* Regular pointer gives a way to retrieve the information, while hash pointer gives a means to retrieve the information as well as verify if the information has changed.

#### Merkel tree: Binary tree with Hash Pointers - Ralph Merkel
* Each of these blocks are hashed using some hash function. Then each pair of nodes are recursively hashed until we reach the root node, which is a hash of all nodes below it.
* This takes (log n) items to be shown and therefore (log n) time to verify;

#### Digital Signatures : (i) Only you can sign, but anyone can verify; (ii) Signature is tied to a particular document;
##### 3 Operations performed - API digital signature
* Generate keys -> provide the input keysize and this generates two keys sk and pk. (sk, pk) := generateKeys(keysize); randomized Algo
* Sign Operation -> sig := sign(sk, message); (sk : secret signing key | pk: public verification key | sig (signature)); random Algo
* Verify Operation -> isValid := verify(pk, message, sig); deterministic Algo;

#### GoofyCoin -> simplest cryptocurrency engine;
* Goofy creates new coins - digital signature of Goofy and anybody can verify it;
* Coins owner can spend it, recepient can pass it to someone else, but double spend can occur;

#### ScroogeCoin 
* Publishes a history of all transaction - block-chain digitally signed by Scrooge;
* 2 kinds of transactions : Createcoin transaction and Paycoin transaction;
##### Rules PayCoins transactions:
1. consumed coins are valid - (the coins were created in previous transactions);
2. consumed coins were not already consumed in some previous transactions (double spend attack);
3. total values of the coins out of the transaction is same as the total coins that went into the transaction.
4. the transaction is signed by all the owners of the consmed coins.

------------------------------------------------------
## 2. Bitcoin achieves Decentralization
------------------------------------------------------
#### Distributed consensus :
* Achieve overall system reliability in the presence of a number of faulty processes which requires processes to agree on some data value that is needed during computation. Examples whether to commit a transaction to a database, etc;

#### "peer to peers" system : 
* They are computer systems/ hardwares which are connected to each other via the Internet.

#### How consensus works in Bitcoin : 
* All nodes have a sequences of blocks of transactions they've reached consensus on.
* Each node has a set of outstanding transactions it's heard about.
* Could have a sequence of blocks that everybody has agreed upon. (Block - A sequence of transactions);

###### Consensus could be hard : Nodes may crash, be malicious, network could be imperfect(crash, latency), etc;
#### Bitcoin nodes have no identities :
* Identity is hard in a  P2P (peer-to-peer) system (decentralized) (no central authority to assign and verify node creation)
* Pseudonymity is a goal of Bitcoin.

#### Consensus Algorithm:
1. New transactions are broadcast to all nodes.
2. Each node collects new transactions into a block.
3. In each round a random node gets to broadcast its block.
4. Other nodes accept the block only if all the transactions in it are valid (unspent, valid crypto signatures, double spend);
5. Nodes express their acceptance of the block by including the hash in the next block they create.

#### Incentives: 
* Incentive 1 : Block reward - Include a special coin-create transaction in the block; Choose a recipient address of this transaction;(12.5 BTC)
* Incentive 2 : Transaction fee - transaction creator can make output value more than the input value, where the remainder is payed as transaction fee.

#### Proof of Work:
* Instead of picking a node, in a decentralized network can allow nodes to compete using their computing power and make it hard to create new identities.

#### Hash puzzles:
* Each block has a "nonce", "previous hash" -> points to the previous block, list of transactions.
* For a node to successfully create a block it must create a hash output that matches that target size and this demands high computation power and is not very easy. 
To create a block , find nonce s.t.
###### H(nonce || prev_hash || tx || ... || tx) is very small

#### 3 properties of POW:
* PoW property 1 :  difficult to compute - about 10^20 hashes/block; (target space - 1 / 10^20 of the output space);
* PoW property 2 : parametrizable cost - Nodes on the peer-to-peer network will automatically re-calculate the target; Goal : average time between blocks = 10 minutes; Mean time to find a block = 10 minutes / fraction of hash power;
* PoW propert 3 : trivial to verify -> gets rid of the centalization property - Nonce must be published as part of block; Other miners simply verify that
    H(nonce || prev_hash || tx || ... || tx) < target
    
### Terminology
--------------
1. Identities - No identities needed, any user can create a pseudonymous key pair at any moment , and any number of them.
2. Transactions - Messages that are broadcast to the Bitcoin peer-to-peer network - instructions to transfer a coin rfom one address to another.
3. P2P network - propagates all new transactions/blocks to all the Bitcoin peer nodes.
4. Block-chain & Consensus -  security . transaction in the block-chain only after confirmations (>6) - part of the consensus chain;
 Orphan block - blocks not a part of the consensus chain - due to double spend, network latency, invalid bliock, etc;
5. Hash Puzzles and Mining - Miners are special type of nodes that compete for creating new blocks and are rewarded for their efforts in terms of Bitcoins.    

#### Bitcoin consensus gives us:
1. Append-only ledger - datastructure that we can only write to and once data is there , it's forever.
2. Decentralized consensus - decentralized protocol for establishing the value of that ledger.
3. Miners to validate transactions. (no double spends);

------------------------------------------------------
## 3. Bitcoin Mechanism
------------------------------------------------------
* Bitcoin Transactions -> Transaction-based ledger
#### A transaction-based ledger (Bitcoin) :
* Transactions explicitly specify the number of inputs and the number of outputs.
* Transactions have unique identifier - "hash pointer".
* Mandatory to consume the output of the previous transaction.
* Requires a finite scan to verify if the transaction is valid.

#### Join Payment :
* A transaction can be signed by two members and output to a particular address. It must be signed by both the inpute members. - Joint Payment (2 inputs, 1 outputs) - Joint Payment (2 inputs, 1 outputs)

#### Real Bitcoin Transaction: 3 parts
1. Metadata : transaction hash, housekeeping (version, locktime, size, etc);
2. Input : previous transactions, signatures;
3. Output : calue, script - public key, verify and checksig operations.

#### Bitcoin Script  
##### Bitcoin scripting language ("Script")
Design goals
 1. Built for Bitcoin (inspired by Forth)
 2. Simple, compact
 3. Support for cryptography (hash functions, compute signatures, verify signatures, etc)
 4. Stack-based language
 5. Limits on time / memory
 6. No looping
 
 ##### Bitcoin script execution example
 <sig> <pubkey> OP_DUP OP_HASH160 <pubKeyHash?> OP_EQUALVERIFY OP_CHECKSIG
 
 1. If data is seen it's pushed onto the stack.
 2. Here - write data into the memory (stack);
 3. OP_DUP -> take value on top of the stack, pop off, and write two copies back to stack. 
 4. OP_HASH160 -> Take the value on top of stack and compute cryptographic hash of it.
 5. OP_EQUALVERIFY -> Check if the two values at the top of stack equal.
 6. OP_CHECKSIG -> Verify the signatures
 
#### Bitcoin script instructions
* Small -> 256 opcodes total (15 disable, 75 reserved)
* Operations - Arithmetic, If/then, Logic/data handling, Crypto!( Hashes, Signature Vwerifications, Multi-signature verification -> Instruction -> OP_CHECKMULTISIG - Built-in support for joint signatures).

#### Applications of Bitcoin scripts :
1. Escrow Transactions - A third party is introduces to resolve any impending conflicts on a executing transaction. It requires 2-of-3 signatures to be a valid transaction. A third party/judge is introduced to resolve the conflict.
2. Green Address - It provides a means to do payment without accessing the block-chain. Instawallet and Mount Gox;
3. Efficient micro-payments - Each transaction is associated with a transaction fee. Instead for small transactiona group of them can be bundled and transaction can occur.

##### "locktime" in Bitcoin transaction metadata - If the lock-time has value other than 0, then that tells moners to not publish this transaction until the time soecified in the variable "lock_time".
*"LOCK_TIME" - Bindex or real-world timestamps before which transactions can't be published.lock 

#### Bitcoin Blocks:
* Single unit of work for miners, limit the length of hash-chain of blocks;
* Bitcoin block structure - 2 different hash-based data structures:
1. Header - Top -> hash-chain of blocks. Each block has a header and a pointer to a previous transaction. 
       - hash, ver, prev_block, time, bits, nonce, mrkl_root, etc;
2. Coinbase Transaction - Hash tree / Merkel tree - of transactions in each block. 
       - in(pre_out - hash, n coinbase); output(value, scriptPubKey);

#### Bitcoin Network :
* It is a P2P network, where all nodes are equal. It runs over TCP and has a random topology - random nodes are peered with random other nodes. New nodes can join at any time. Network is dynamic, forget non-responding nodes after 3 hours.

#### Join the Bitcoin P2P network - Launch a new node and want to join the network :
* start with a simple message to get to one node(seed node) on the network.
* Find the seed node and send a special message - asking for all the peers of the seed node.
* The contacted seed node replies with peered nodes.
* The newly launched node then contacts the seed nodes peers and asking the new seed nodes for their peers.
* Eventually a list of peers will be gathered by the newly lauched node - which then chooses which ones to peer with.
* Then the new node will be a fully functional node in the Bitcoin network. (Memeber of the blockchain);
  
#### Transaction propagation (flooding) :
* Network maintains the block-chain, so if any transaction is to be published, the entire network needs to know about it.
* There is a simple flooding algorithm that takes care of it.

#### Fully-validating nodes :
* Permantelty connected, Store entire block chain, Hear and forward every node/transaction, Track the UTXO;

#### Thin / SPV(Simple Payment Verification) client (not fully validating) :
* Don't store everything, Store block headers only. Request transactions as needed. (To verify incoming payments). Trust fully-validating nodes. Inexpensive;

------------------------------------------------------
## 4. Store and Use Bitcoin
------------------------------------------------------
#### Simple Local Storage :
* Wallet software : Keeps track of coins, provides nice user interface.
* Encoding Address : Payments are made via exchanging address. The address needs to be encoded or conveyed to make a transaction possible. Addresses exchanged as : Encoded as text string; QR (Quick Response) code;

#### Hot & Cold Storage :
* Hot storage -> Online (Conveninet but risky) - (money in your wallet); <-------|
                                                                                 | <--- Separate Keys/Addresses
* Cold storage -> Offline (Archival but safer) - (money in the safe);    <-------| 

* In practice -> hot storage is online and cold storage is offline and hence they cannot connect to each other.
-> But the hot storage know the address at which the cold storage accepts payment, therefore the hot storage can send coins across to the cold storage, even while cold storage is offline.
-> Next time the cold storage connects it will receive the information from a block-chain about those transfers.

#### Splitting and Sharing Keys :
* Storing the Key at a certain place alone - could cause a single point of failure problem. Inorder to avoid this situation, the key needs to be shared or split.
##### Secret sharing
* Idea: split secret into N pieces, such that:
*   given K pieces, can reconstruct the secret
*   given fewer than K pieces, don't learn anything

#### Multi-sig :
-> Lets you keep shares apart, approve transaction without reconstructing key at any point.
-> It enables to store large-bodies of cold-stored coins in a way that is relatively secure and that requires action from multiple people before anything drastic can happen.

#### Payment Services :
* customer wants : to pay with Bitcoin
* Merchant wants:  - to receive dollars;  - simple deployment;  - low risk (tech risk, security risk, exchange rate risk);
    The merchant will first go to a payment services website and fill out a form.
    On click of the Generate Bitcoin Code , a HTML will be created, that the merchant can drop into their website.
    Then when a customer wants to make payment, on pushing that button a payment will be made.
 ##### End Result
  * customer : pays Bitcoins.
  * merchant : gets dollars, minus a small percentage.
  * payment service: gets Bitcoins, pays dollars, absorbs risk : security, exchange rate, - needs exchange Bitcoins for dollars, in volume (Participate in the market). 
 
#### Transaction Fees : 
 * Whenever a transaction is put into a blockchain, then that transaction might pay a transaction fee.
 * Transaction fee = value of inputs - value of outputs. fees goes to miners who records the transactions.
 
#### Current consensus fees
* No fee if:
    - tx less than 1000 bytes in size and
    - all outputs are 0.01 BTC or larger, and
    - priority is large enough.

 * Priority = (sum of inputAge*inputValue) / (trans size) ;  Otherwise fee is 0.0001 BTC per 1000 bytes.
 * Approx transaction Size : 148 N(inputs) + 34 N(outputs) + 10
 
 #### Currency Exchange Markets :
 * Markets on which you can trade Bitcoins against fiat currencies.
 
 #### Simple Model of transaction-demand :
 * T = total transaction value mediated via BTC ($ / sec);
 * D = duration that BTC is needed by a transaction (sec); (held out of sirculation; Payer buys BTc from market and receiver sells them back to the market);
 * S = supply of BTC (not including BTC help as long-term investments) (BTC);
 * P = Price of BTC ($ / BTC);
      
###### Supply = S / D; (Bitcoin become available per second);                                                      
###### Demand = T / P; (Bitcoins needed per second);
###### Equilibrium   = P = ( T * D) / S;                        
  
* Supply > Demand - Price goes down, hence demand increases.
* Demand > Supply - Price goes up, hence demand decreases.

------------------------------------------------------
## 5. Bitcoin Mining
------------------------------------------------------
#### Mining Bitcoins in 6 easy steps - (How to become a Miner)
1. Join the network, (become a Bitcoin node), listen for transactions.
    - Validate all proposed transactions.
2. Listen for new blocks, maintain block chain.
    - When a new block is proposed, validate it.
3. Assemble a new valid block.
4. Find the nonce to make your block valid. (Hard - computationally).
5. Hope everybody accepts your new block.
6. Profit! (>25 BTC / block -> $15,000).

#### Mining difficulty "target" (2014-08-07)
* The hash of any valid block needs to below the value shown below.
* It's a 256-bit hash output value. (Using SHA 256 to hash function)
* Atleast first 64-bit are 0's
* Current difficulty = 2 ^66.2 ~ 84 quintillion.

#### Setting the minning difficulty
* Every two weeks compute: It's based on how efficient the miners were over the previous two weeks.

###### next_difficulty = previous_difficulty * ((2 weeks) / (time to mine last 2016 blocks))
* Time to mine last 2016 blocks -> Expected number of blocks in 2 weeks at 10 minutes/block
* Over time mining difficulty keeps exponentail increase.
* On an average a block is to be found every 10 minutes(10 - 9 -8 minutes);

#### Mining Hardware :
##### SHA- 256 : 
* It is a general purpose crypotographich hash function. Part of SHA-2 family : SHA-224, SHA-384, SHA-512;
* Published in 2001, Designed by the NSA, Remains unbroken cryptographically. (Weaknesses known);
* SHA-3 (replacement) under standardization.

##### CPU Minig : First generation of mining
* It searched over nonces in a simple linear manner, computed SHA-256 twice in software and checked if the results was a valid block.
Throughput of a high-end PC = 10-20 MHz ~ 2^24; 139,461 years on an average to find a block.

##### GPU Mining : Second generation of mining
* GPU designed for high-performance graphics. high parallelism. high throughput. Implemented in OpenCL(Open Computing Language) , Later: hacks for specific cards.
* Throughput on a good card = 20 - 200 MHz ~ 2^27, ~ 173 years on average to find a block w / 100 cards!

##### FPGA mining : Field Programmable Gate Area
* Implemented in Verilog. Higher performance than GPUs - excellent performance on bitwise operations. Better cooling. Extensive customization, optimization. (bit fidling).
* Throughput on a good card = 100-1000 MHz ~ 2^30; 25 years on average to find a block w/100 boards!

##### Bitcoin ASICs (Application Specific Integrated Circuits)
* Designed to be run constantly for life. Requires significant expertise, long lead-times. Perhaps the fastest chip developed ever.
* Throughput = 2 TH/s , ~ 14 months on an average to find a block. 
 
#### Profressional Mining Centers :
* Prerequisite : Cheap power, Good Network, Cool Climate;

#### Energy consumption aspects of Bitcoin mining - 3 steps
1. Embodied energy : used to manufacture mining chips and other equipment.
(Includes physical mining - digging out rare metals, coppers -> IC's, etc; Manufacture into Bitcoin mining ASIC);
 *Should decrease over time. (since sufficient chip will be there, more resources online, etc); Returns to scale. (optimized on large scale)
 
2. Electricity : used to perform computation. (plug into wall - draws electricity - and perform computation).
*Should increase over time. Return to scale. (optimized on large scale);

3. Cooling : required to protect equipment.  
*costs more with increased scale! (not optimized in a large scale);

##### I. Estimating energy usage : top-down
* Each block worth approximately US$15,000 (25 BTC that's found every 10 minutes). Approximately $25/s generated. Industrial electricity (US): $0.03/MJ- $0.10/kWh;
 * Lower bound = $25/s * 1/$0.03 ~ 900 MJ/s;
 
##### II. Estimating  energy usage: bottom-up
* Best claimed efficieny : 1GHz/W. (mining rigs);Network hash rate: 150,000,000 Ghz.
* Lower bound on electricity = 150,000,000 GHz / 1 GHz/W = 150 MHz W = 150MW;

#### Mining Pools :
* Goal : pool participants all attempt to mine a block with the same coinbase recipient. send money to key owned by pool managers;
* Distribute revenues to members based on how much work they have performed. minus the cut for pool manager.

#### Mining shares:
Idea: prove work done with "near-valid blocks" (shares);
* It might be hard to find a block with first 66-bits 0's -> valid block; almost valid block : many leading 0's; (40-bit, 50 bit, etc); No way to fake.

#### Mining pool variations:
1. Pay per share: flat reward per share
  * typically minus a significant fee. miners get payed even if they don't find valid blocks.
2. Proportional: typically since last block.
   * lower risk for pool manager. More work to verify. (Miners are payed based on the work they do/ shares);
3. "Luke-jr" approach : no management fee.
   * miners can only get paid out in BTC. (New entrant don't make money for a while and then they even out.) Pool owner keeps spread;
   
------------------------------------------------------
## 6. Bitcoin Anonymity
------------------------------------------------------
* Anonymity = pseudonymity + unlinkability;
* unlinkability - Different interactions of the same user with the system should not be linked with each other.

#### Mixing :
* To protect anonymity, use intermediary. Users send transactions to intrermediary service.
* The intermediary doesn't keep track of the user and the transaction. 
* When the user withdraws an amount it is drawn from a random address from the intermediary. So there is no link from the input transaction address to the output transaction address, and no one can identify it from an external public chain on something else.

#### Coinjoin :
* The main proposal for a decentralized mixing is called Coinjoin. Proposed by Greg Maxwell,  Bitcoin core developer.
* Multiple users can interact in a single interaction , via different input addresses and output addresses.
* An adversary looking at the block chain will not able to tell which transaction corresponds to which user.

#### Coinjoin Algorithm :
1. Find peers who want to mix.
2. Exchange input/output addresses.
3. Construct transaction.
4. Send it around, collect signatures. (Before signing , each peers checks if her output is present).  (Security property);
  (Incase any of the peers don't sign the transaction, then it collapses);
5. Broadcast the transaction.
   (Any number of valid peers can broadcast the transaction).

#### Zerocoin and Zerocash :
##### Zerocoin : Extension of Basecoin (Bitcoin-like Altcoin.);
* A Zerocoin is a cryptographic proof that you owned a Basecoin and made it unspendable.
* Miners can verify this proof. Gives you the right to redeem a new Basecoin. (like poker chips).

##### Zerocash
 * It uses a cryptographic tool called Snarks.
 * Zerocoin without Basecoin.
 * Upshot -  1. Different crypto for proofs (More efficient); 2. Proposal to run system without Basecoin.
 * All transactions are zerocoins. Splitting and mergining supported. Put transaction value inside the envelope (H(S, r)); Ledger merely records the existence of the transactions.

### 5 Levels of Anonymity :
 
  | System | Type | Anonymity attacks  | Deployability|
  | --- | ---| --- | --- |
  | Bitcoin      | Pseudonymous         | Tx graph analysis                  | Default                |
  | Single Mix   | Mix                  | Tx graph analysis, bad mix         | Usable today           |
  | Mix chain    | Mix                  | Side channels, bad mixes, peers    | Bit-coin compatible    |   
  | Zerocoin     | Cryptographic mix    | Side channels (possible)           | Altcoin                |     
  | Zerocash     | Untraceable          | None                               | Altcoin, tricky setup  |
  
  
Tx graph analysis - unlinkability test - linking address and change address.

#### Anonymous communications
* Bunch of senders and bunch of recipients, and messages are routed from senders through recipients through the anonyous network.
* There might be an attacker, in the threat model , the attacker controls many things - nodes, edges/links, internal nodes;

#### Tor
* Tor helps to achieve anonymity(pseudonomity + unlinkability) in the network.
* Alice wants to communicate with Bob, she pre-selects a path through a set of routers (3 routers - fixed number) - Tor routers.
* Security property : Safish if atleast one router is honest - anonymity can be achieved.
* Key challenge -> Hiding routing information. (each routing information would have timestamp, destination and source ip address,
therefore the addresses and the information are encrypted);

------------------------------------------------------
## 7. Community, Politics and Regulations
------------------------------------------------------
#### Consensus in Bitcoin :
* Bitcoin relies on the formation of consensus among people. There are three kind of consensus that are required for Bitcoins to operate.
##### I. Consensus about Rules :
Agree on: what makes a transaction valid. what makes a block valid. how P2P nodes should behave. protocols and data formats needed for Bitcoin to operate.
##### II. Consensus about History :
Agree on contents of the blockchain: which transactions have occured. which coin exists and who owns them.
##### III. Consensus that Coins are Valuable:
 General agreement that coins have value.

#### Bitcoin Core Software :
* open source (MIT licence); The most widely used Bitcoin software;
* "Bitcoin core is the de facto rule book of Bicoin."
* Bitcoin Improvements Proposals (BIPs)

#### Stakeholders : Who's in charge?
1. Claim: Bitcoin core developers have the power - They write the rulebook. Almost everybody uses their code, follows their rules.
2. Claim: Miners have the power - Miners write the history. History will be consistent with miners' consensus rules.
3. Claim: Investors have the power - Investors determine whether Bitcoins have value. In case of hard-fork, investors decide which branch wins.
4. Claim: Merchants and their customers have the power - They generate the primary demand for Bitcoin. - They drive the long-term price for Bitcoin. - Investors are just guessing where merchants and customers will go.
5. Claim: Payment services have the power - They are the ones that really handle transactions. (give dollars);- So they drive primary demand. - Merchants, customers, and investors will follow them.
 
#### Regulation : When markets fail and produce bad outcomes, regulations can address the failure.
----------------------------------------------------------------------------------------------------------------------------------------
