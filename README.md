# pi-back-grupo-3
Projeto Integrado , Back-end grupo 3

<h3>Commits 2, 3 (remote -> main)</h3>
<p>
  NAS MIGRATIONS:
  1. Criados arquivos V11 e V12;
  2. inseridas colunas "images" e "description" em V3 e V4

  EM MODEL:
  1. Model Book

    1.1 Adicionados atributos "image" e "description";
    1.2 getter e setter para os atributos "image" e "description";
    1.3 NÃO FORAM FEITAS ALTERAÇÕES NO EQUALS E NO HASHCODE DESSA MODEL

  2. Model Client

    2.1 Entidade criada com @OneToMany com List<Order>

  3. Model Order

    3.1 Entidade criada com @ManyToOne com client_id
</p>
