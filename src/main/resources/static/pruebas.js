const selectElement = document.getElementById("info-select");
const inputElement = document.getElementById("info-input");
const editButton = document.getElementById("edit-button");

// Evento del botón para habilitar la edición
editButton.addEventListener("click", () => {
  // Habilitar o deshabilitar el select e input según el estado actual
  if (selectElement.disabled) {
    selectElement.disabled = false;
    inputElement.value = ""; // Limpiar el valor del input al habilitar la edición
  } else {
    selectElement.disabled = true;
    // Actualizar el valor del input con el valor seleccionado del select
    inputElement.value = selectElement.value;
  }
});
