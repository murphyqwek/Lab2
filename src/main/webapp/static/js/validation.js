function checkStringFormat(rawValue) {
    rawValue = rawValue.replace(',', '.');
    const regex = /^-?\d*\.?\d*$/;
    
    return regex.test(rawValue);
}



function checkForNull(value) {
    return !(value === '' || value === null || value === undefined);
}


function validateInput(variableName, value, min, max, decimalPlaces = 3) {
    if (!checkForNull(value)) {
        return {
            isValid: false,
            message: `Значение ${variableName} не может быть пустым`,
            correctedValue: ''
        };
    }
    
    if (!checkStringFormat(value)) {
        return {
            isValid: false,
            message: `Значение ${variableName} должно быть числом`,
            correctedValue: ''
        };
    }

    const numValue = parseFloat(value);
    if (isNaN(numValue)) {
        return {
            isValid: false,
            message: `Неверный формат числа для значения ${variableName}`,
            correctedValue: ''
        };
    }

    const parts = value.split('.');
    if (parts.length > 1 && parts[1].length > decimalPlaces) {
        return {
            isValid: false,
            message: `Слишком много знаков после запятой для ${variableName}. Максимум: ${decimalPlaces}`,
            correctedValue: ''
        };
    }
    
    if (numValue < min || numValue > max) {
        return {
            isValid: false,
            message: `Значение ${variableName} должно быть от ${min} до ${max}. Текущее значение: ${numValue}`,
            correctedValue: ''
        };
    }
    
    return {
        isValid: true,
        message: 'OK',
        correctedValue: numValue
    };
}


function checkX(xRawValue) {
    return validateInput('X', xRawValue, -3, 5);
}

function checkY(yRawValue) {
    return validateInput('Y', yRawValue, -3, 3);
}

function checkR(rRawValue) {
    return validateInput('R', rRawValue, 2, 5);
}

function validateAllNums(xValue, yValue, rValue) {
    var xResult = checkX(xValue);
    var yResult = checkY(yValue);
    var rResult = checkR(rValue);

    if(!xResult.isValid) {
        return { isAllValid: false, message: xResult.message };
    }

    if(!yResult.isValid) {
        return { isAllValid: false, message: yResult.message };
    }

    if(!rResult.isValid) {
        return { isAllValid: false, message: rResult.message };
    }

    return { isAllValid: true, message: "" };
}