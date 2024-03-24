"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.isDefined = exports.hasTemplateBlock = void 0;
function hasTemplateBlock(context) {
    const sourceCode = context.getSourceCode();
    const { ast } = sourceCode;
    return Boolean(ast.templateBody);
}
exports.hasTemplateBlock = hasTemplateBlock;
function isDefined(item) {
    return item !== null && item !== undefined;
}
exports.isDefined = isDefined;
