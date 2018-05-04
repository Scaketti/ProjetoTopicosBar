# DESCRIÇÃO

Desenvolva um sistema para um bar. Este sistema terá um terminal (CLIENTE) em cada mesa. Este se
comunicará ao computador central (SERVIDOR) que será responsável por todo o gerenciamento de
informações. Haverá um terminal administrador (CLIENTE) que será o caixa. A seguir será descrito as
responsabilidades de cada um:

* CLIENTE (Terminais)
	* Conterá o cardápio com todos os pratos e bebidas do local;
	* Será possível realizar um pedido do próprio terminal, necessitando do garçom apenas
para levar o pedido a mesa;
	* Apresentará aos clientes o que já foi consumido, com seus respectivos valores, além do
valor parcial da conta até o momento.

* CLIENTE (Caixa)
	* Será responsável pelo cadastro e edição de produtos do estabelecimento, bem como o
reabastecimento do estoque;
	* Responsável pelo fechamento de uma determinada conta;
	* Controle de caixa

* SERVIDOR
	* Apenas o servidor poderá realizar acesso ao banco de dados;
	* Controlará o estoque, ou seja, quando um produto estiver em falta, ele não deixará que
os terminais realizem pedidos daquele produto;
	* A cada novo pedido realizado pelos clientes, ou a cada movimentação realizada pelo
caixa, o servidor deve exibir em sua própria interface gráfica, o nome do cliente, qual a
movimentação ocorreu (cadastro de produto, reabastecimento de estoque, pedido), data
e hora que ocorreu a movimentação.