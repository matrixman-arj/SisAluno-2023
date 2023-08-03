export function mask_decimal(input){
    if(typeof input === "undefined" || input == null)
    return 0;
  
    let valor = '';
  
    if(typeof input.value !== "undefined"){
      valor = input.value;
    } else {
      valor = input;
    }
  
    if(valor.length == 2)
    return valor;
  
     let v = valor;
     v = v.replace(/\D/g, '');
     v = v.replace(/(\d{1,2})$/, ',$1');
     v = v.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
   return v;
  }