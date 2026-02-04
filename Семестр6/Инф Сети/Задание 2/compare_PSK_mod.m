EbNoVec = -2:1:21;
steps = 1e7;
SERVec_PSK = []; 

load_system('compare_psk');
opts = simset('SrcWorkspace', 'Current', 'DstWorkspace', 'Current');
set_param('compare_psk/AWGN Channel PSK', 'EsNodB', 'EbNodB+10*log10(4)');
set_param('compare_psk/Error Rate Calculation PSK', 'numErr', '1e2');
set_param('compare_psk/Error Rate Calculation PSK', 'maxBits', 'steps');

for n = 1:length(EbNoVec)
    fprintf(1, 'Running %2d lap from %d\n', n, length(EbNoVec));
    EbNodB = EbNoVec(n);
    sim('compare_psk', steps, opts);
    SERVec_PSK(n, :) = PSK_SER;
    
    semilogy(EbNoVec(n), SERVec_PSK(n, 1), 'go-');
    hold on;
    drawnow;
end
hold off;

M = length(const);
k = log2(M);
d_min = min(pdist([real(const), imag(const)])); 
EsNoLin = k * 10.^(EbNoVec./10);


A = sqrt(sum(abs(const).^2) / length(const));

distances = pdist([real(const), imag(const)]);
a = min(distances) / 2;

za = a / A * sqrt(2 * k * (10.^(EbNoVec / 10)));

P = 0.5 * erfc(za / sqrt(2));


SER_PSK_16_an = P;


EbNoVec_cut = EbNoVec(1:end-3); 
SER_PSK_16_an_cut = SER_PSK_16_an(1:end-3); 


figure;
semilogy(EbNoVec, SERVec_PSK(:,1), 'go', ... % Зеленые точки (симуляция)
         EbNoVec_cut, SER_PSK_16_an_cut, 'mx:', 'LineWidth', 1.2); % Розовые точки (аналитика)
legend('PSK sim', 'PSK analit', 'Location', 'SouthWest');
grid on;
xlabel('Eb/No (dB)', 'FontSize', 14);
ylabel('SER', 'FontSize', 14);
title('Symbol Error Rate (SER) with Distance-Based Calculation', 'FontSize', 14);
hold off;

fprintf('--- Итоговые значения ---\n');
fprintf('Средняя мощность созвездия (A) = %.4f\n', A);
fprintf('Минимальное расстояние (d_min) = %.4f\n', d_min);