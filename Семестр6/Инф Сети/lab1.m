clear;

inphase = [-5 -4 -3 -2 -1];
quadr = [0 2 5 2 0];

inphase = [inphase; inphase; -inphase; -inphase];
inphase = inphase(:);

quadr = [quadr; -quadr; quadr; -quadr];
quadr = quadr(:);

unique_points = unique([inphase, quadr], 'rows');
inphase = unique_points(:, 1);
quadr = unique_points(:, 2);

const = inphase + quadr * 1j;

scatterplot(const, 1, 0, '*');
grid on;
