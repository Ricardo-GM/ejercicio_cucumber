@shopCart
Feature: Carrito de Compras
  Como usuario quiero utilizaar el carrito de compras para añadir o quitar productos
  @AñadirProducto
  Scenario: Añadir un producto al carrito de compras
    Given El usuario ha iniciado sesion correctamente
    And El usuario se encuentra en la pantalla de Inventario
    When El usuario da click en el boton ADD TO CART de un producto
    Then El usuario visualiza que el producto se añadio al carrito de compras
  @RemoverProducto
  Scenario: Quitar un producto del carrito de compras
    Given El usuario ha iniciado sesion correctamente
    And El usuario añade un producto al carrito de compras
    When El usuario ingresa a la pantalla del Carrito de Compras
    And El usuario remueve el producto del carrito de compras
    Then El usuario visualiza que el producto se removio del carrito de compras
